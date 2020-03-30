package com.http.es.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.http.es.service.response.Aggregate;
import com.http.es.service.response.Record;
import com.http.es.service.response.ResponseExtractor;
import com.http.es.service.response.SearchResponse;
import com.http.es.service.response.aggregate.Filters;
import com.http.es.service.response.aggregate.Histogram;
import com.http.es.service.response.aggregate.Metric;
import com.http.es.service.response.aggregate.Percents;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EsResponseExtractor implements ResponseExtractor {
    private static final Pattern AGG_TYPE_PATT = Pattern.compile("\\w+terms#\\w+");

    @Override
    public SearchResponse extract(Object obj) {
        SearchResponse response = new SearchResponse();

        JSONObject jsonResponse = (JSONObject) obj;
        JSONObject hits = jsonResponse.getJSONObject("hits");
        if (hits != null) {
            response.setTotal(hits.getIntValue("total"));
            response.setRecords(extractRecords(hits.getJSONArray("hits")));
        }

        JSONObject aggregations = jsonResponse.getJSONObject("aggregations");
        if (aggregations != null) response.setAggregates(extractAggregates(aggregations));

        return response;
    }

    private List<Record> extractRecords(JSONArray hits) {
        List<Record> records = new ArrayList<>();
        for (int i = 0; i < hits.size(); i++) {
            JSONObject recordJson = hits.getJSONObject(i);
            Record record = new Record();
            record.put("_id", recordJson.getString("_id"));
            record.putAll(recordJson.getJSONObject("_source"));
            records.add(record);
        }

        return records;
    }

    private List<Aggregate> extractAggregates(JSONObject aggregations) {
        List<Aggregate> aggregates = new ArrayList<>();

        aggregations.forEach((k, v) -> {
            Aggregate aggregate;

            Matcher matcher = AGG_TYPE_PATT.matcher(k);
            if (k.startsWith("range#") || k.startsWith("histogram") || matcher.matches()) {
                JSONArray buckets = aggregations.getJSONObject(k).getJSONArray("buckets");

                Histogram histogram = new Histogram(extractAggName(k));
                for (int i = 0; i < buckets.size(); i++) {
                    JSONObject histogramResult = buckets.getJSONObject(i);
                    histogram.add(histogramResult.getString("key"), histogramResult.getInteger("doc_count"));

                    //递归
                    List<Aggregate> childrenOfKey = extractAggregates(histogramResult);
                    if (childrenOfKey.size() > 0) histogram.setChildrenByKey(histogramResult.getString("key"), childrenOfKey);
                }
                aggregate = histogram;
            } else if (k.startsWith("sum#") || k.startsWith("avg#")) {
                Number value = (Number) aggregations.getJSONObject(k).get("value");
                aggregate = extractMetric(extractAggName(k), value);
            } else if (k.equals("filters#filters")) {
                aggregate = extractFilters(aggregations.getJSONObject(k).getJSONArray("buckets"));
            } else if (k.startsWith("min#")) {
                Number value = (Number) aggregations.getJSONObject(k).get("value");
                aggregate = extractMetric(extractAggName(k), value);
            } else if (k.startsWith("cardinality#")) {
                Number value = (Number) aggregations.getJSONObject(k).get("value");
                aggregate = extractMetric(extractAggName(k), value);
            } else if (k.startsWith("tdigest_percentiles#")) {
                JSONObject values = aggregations.getJSONObject(k).getJSONObject("values");
                aggregate = extractPercents(extractAggName(k), values);
            } else if (k.startsWith("filter#")) {
                aggregate = new Metric(extractAggName(k), (Number) aggregations.getJSONObject(k).get("doc_count"));
            } else return;

            aggregates.add(aggregate);
        });

        return aggregates;
    }

    private Aggregate extractPercents(String name, JSONObject values) {
        return new Percents(name, values);
    }

    private String extractAggName(String key) {
        int index = key.indexOf("#");
        if (index < 0) return key;
        if (key.length() - index < 1) return key;

        return key.substring(index + 1);
    }

    private Aggregate extractMetric(String name, Number sum) {
        return new Metric(name, sum);
    }

    //filters聚合的结果依赖于顺序,没有name标记
    private Filters extractFilters(JSONArray buckets) {
        Filters filters = new Filters();
        for(int i = 0; i < buckets.size(); i ++) {
            filters.add(buckets.getJSONObject(i).getIntValue("doc_count"));
        }

        return filters;
    }
}
