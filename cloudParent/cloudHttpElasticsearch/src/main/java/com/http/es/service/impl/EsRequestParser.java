package com.http.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.http.es.service.RequestParser;
import com.http.es.service.response.aggregate.Avg;
import com.http.es.service.response.aggregate.Cardinality;
import com.http.es.service.response.aggregate.Filters;
import com.http.es.service.response.aggregate.Histogram;
import com.http.es.service.response.aggregate.Min;
import com.http.es.service.response.aggregate.Percentiles;
import com.http.es.service.response.aggregate.Script;
import com.http.es.service.response.aggregate.Sum;
import org.apache.commons.lang3.StringUtils;
import request.Aggregator;
import request.FieldType;
import request.Filter;
import request.SearchRequest;
import request.Sorter;
import request.filter.AndFilter;
import request.filter.EqFilter;
import request.filter.ExistsFilter;
import request.filter.KeywordFilter;
import request.filter.NotEmptyFilter;
import request.filter.NotFilter;
import request.filter.OrFilter;
import request.filter.PhraseFilter;
import request.filter.PrefixFilter;
import request.filter.RangeFilter;
import request.filter.ScriptFilter;
import request.filter.SuffixFilter;
import request.filter.WildcardFilter;

import java.util.List;
import java.util.stream.Collectors;


public class EsRequestParser implements RequestParser {
    @Override
    public String parse(SearchRequest request) {
        final StringBuilder body = new StringBuilder();

        body.append("{")
                .append("\"from\":")
                .append(request.getStart())
                .append(",")
                .append("\"size\":")
                .append(request.getCount());

        if (!request.getFields().isEmpty()) body.append(",\"_source\":").append(JSON.toJSONString(request.getFields()));
        if (!request.getFilters().isEmpty() && request.isConstantScore()) body.append(",\"query\":{\"constant_score\":{\"filter\":").append(parseFilter(new AndFilter(request.getFilters()))).append("}}");
        if (!request.getFilters().isEmpty() && !request.isConstantScore()) body.append(",\"query\":").append(parseFilter(new AndFilter(request.getFilters())));
        if (!request.getSorters().isEmpty()) {
            body.append(",\"sort\":[");
            body.append(StringUtils.join(request.getSorters().stream().map(sorter -> parseSorter(sorter)).collect(Collectors.toList()), ","));
            body.append("]");
        }
        if (!request.getAggregators().isEmpty()) {
            body.append(",\"aggs\":{");
            body.append(StringUtils.join(request.getAggregators().stream().map(aggregator -> parseAggregator(aggregator)).collect(Collectors.toList()), ","));
            body.append("}");
        }

        body.append("}");

        return body.toString();
    }

    private String parseFilter(Filter filter) {
        if (filter.isInvalid()) throw new RuntimeException("无效的filter: " + JSON.toJSONString(filter));

        if (filter instanceof AndFilter) {
            List<Filter> subFilters = ((AndFilter) filter).getSubFilters();
            if (subFilters.isEmpty()) return "";
            String json = "{ \"bool\": ";
            json += "{\"must\": [";
            for (int i = 0; i < subFilters.size(); i++) {
                if (i > 0) json += ",";
                json += parseFilter(subFilters.get(i));
            }
            json += "]}}";
            return json;
        } else if (filter instanceof OrFilter) {
            List<Filter> subFilters = ((OrFilter) filter).getSubFilters();
            if (subFilters.isEmpty()) return "";
            String json = "{ \"bool\": ";
            json += "{\"should\": [";
            for (int i = 0; i < subFilters.size(); i++) {
                if (i > 0) json += ",";
                json += parseFilter(subFilters.get(i));
            }
            json += "]}}";
            return json;
        } else if (filter instanceof RangeFilter) {
            RangeFilter rangeFilter = (RangeFilter) filter;
            String lowMethod = rangeFilter.isIncludeLower() ? "gte" : "gt";
            String upMethod = rangeFilter.isIncludeUpper() ? "lte" : "lt";
            if (rangeFilter.getLower() == null && rangeFilter.getUpper() == null) throw new RuntimeException("无效的filter类型");
            else if (rangeFilter.getLower() == null) {
                return "{\"range\":{\"" + rangeFilter.getField() + "\":{\"" + upMethod + "\":" + rangeFilter.getUpper() + "}}}";
            } else if (rangeFilter.getUpper() == null) {
                return "{\"range\":{\"" + rangeFilter.getField() + "\":{\"" + lowMethod + "\":" + rangeFilter.getLower() + "}}}";
            } else {
                return "{\"range\":{\"" + rangeFilter.getField() + "\":{\"" + lowMethod + "\":" + rangeFilter.getLower() + ",\"" + upMethod + "\":" + rangeFilter.getUpper() + "}}}";
            }
        }else if(filter instanceof KeywordFilter) {
            KeywordFilter keywordFilter = (KeywordFilter) filter;
            return "{\"query_string\": {\"query\": \"" + keywordFilter.getValue() + "\"}}";
        } else if (filter instanceof EqFilter) {
            EqFilter eqFilter = (EqFilter) filter;
            if (eqFilter.getValue() instanceof String) return "{\"term\": " + "{\"" + eqFilter.getField() + ".keyword\":\"" + eqFilter.getValue() + "\"}}";
            else return "{\"term\": " + "{\"" + eqFilter.getField() + "\": " + eqFilter.getValue() + "}}";
        } else if (filter instanceof SuffixFilter) {
            SuffixFilter suffixFilter = (SuffixFilter) filter;
            return "{\"wildcard\":{\"" + suffixFilter.getField() + "\":\"*" + suffixFilter.getSuffix() + "\"}}";
        } else if (filter instanceof ExistsFilter){
            ExistsFilter existsFilter = (ExistsFilter) filter;
            return "{\"exists\": {\"field\": \"" + existsFilter.getField() + "\"}}";
        } else if (filter instanceof NotEmptyFilter) {
            NotEmptyFilter notEmptyFilter = (NotEmptyFilter) filter;
            return "{\"bool\":{\"must\":{\"exists\":{\"field\":\"" + notEmptyFilter.getField() + "\"}},\"must_not\":{\"term\":{\"" + notEmptyFilter.getField() + ".keyword\":\"\"}}}}";
        } else if (filter instanceof PhraseFilter) {
            PhraseFilter phraseFilter = (PhraseFilter) filter;
            return "{\"match_phrase\": " + "{\"" + phraseFilter.getField() + "\": \"" + phraseFilter.getKeyword() + "\"}}";
        } else if (filter instanceof NotFilter){
            NotFilter notFilter = (NotFilter) filter;
            return "{\"bool\": {\"must_not\": " + parseFilter(notFilter.getFilter()) + "}}";
        } else if (filter instanceof PrefixFilter) {
            PrefixFilter prefixFilter = (PrefixFilter) filter;
            return "{\"wildcard\":{\"" + prefixFilter.getField() + ".keyword\":\"" + prefixFilter.getPrefix() + "*\"}}";
        }  else if (filter instanceof WildcardFilter) {
            WildcardFilter wildcardFilter = (WildcardFilter) filter;
            return "{\"wildcard\":{\"" + wildcardFilter.getField() + ".keyword\":\"*" + wildcardFilter.getValue() + "*\"}}";
        } else if (filter instanceof ScriptFilter) {
            ScriptFilter scriptFilter = (ScriptFilter) filter;
            return "{\"script\":{\"script\": {\"inline\": \"" + scriptFilter.getScript() + "\"}}}";
        } else {
            throw new RuntimeException("不支持的Filter类型");
        }
    }

    private String parseSorter(Sorter sorter) {
        if (sorter.isInvalid()) throw new RuntimeException("无效的sorter: " + JSON.toJSONString(sorter));
        return "{\"" + sorter.getField() + (sorter.getType() == FieldType.TEXT ? ".keyword" : "") + "\":{\"order\":\"" + sorter.getOrder() + "\",\"unmapped_type\":\"long\",\"missing\":\"_last\"}}";
    }

    private String parseAggregator(Aggregator aggregator) {
        if (aggregator instanceof Histogram) return parseHistogramAggregator(aggregator);
        else if (aggregator instanceof Sum) return parseSumAggregator(aggregator);
        else if (aggregator instanceof Avg) return parseAvgAggregator(aggregator);
        else if (aggregator instanceof Filters) return parseFiltersAggregator(aggregator);
        else if (aggregator instanceof Min) return parseMinAggregator(aggregator);
        else if (aggregator instanceof Cardinality) return parseCardinalityAggregator(aggregator);
        else if (aggregator instanceof Percentiles) return parsePercentilesAggregator(aggregator);
        else if (aggregator instanceof Script) return parseScriptFilterAggregator(aggregator);
        else throw new RuntimeException("不支持的聚合类型: " + JSON.toJSONString(aggregator));
    }

    private String parseHistogramAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Histogram histogram = (Histogram) aggregator;

        if (!histogram.getInterval().equals(0))
            return "\"" + histogram.getField() + "\":{\"histogram\":{\"field\":\"" + histogram.getField() + "\", \"interval\": " + histogram.getInterval() + ",\"extended_bounds\":{\"min\":" + histogram.getMin() + ",\"max\":" + histogram.getMax() + "},\"missing\": 0, \"offset\": " + histogram.getOffset() + "}, " + parseChildAggregator(histogram.getChilds()) + "}";

        if (histogram.getDelims() == null || histogram.getDelims().isEmpty())
            return "\"" + histogram.getField() + "\":{\"terms\":{\"field\":\"" + histogram.getField() + (histogram.getType() == FieldType.TEXT ? ".keyword" : "") + "\", \"size\": 100000}, " + parseChildAggregator(histogram.getChilds()) + "}"; // terms query size

        String ranges = "";

        ranges += "{\"key\": \"" + histogram.getCustomNames().get(0) + "\", \"to\": " + histogram.getDelims().get(0).toString() + "},";
        for (int i = 0; i < histogram.getDelims().size() - 1; i++) {
            ranges += "{\"key\": \"" + histogram.getCustomNames().get(i + 1) + "\", \"from\": " + histogram.getDelims().get(i).toString() + ", \"to\": " + histogram.getDelims().get(i + 1).toString() + "},";
        }
        ranges += "{\"key\": \"" + histogram.getCustomNames().get(histogram.getDelims().size()) + "\", \"from\": " + histogram.getDelims().get(histogram.getDelims().size() - 1).toString() + "}";

        String json = "\"" + histogram.getField() + "\":" +
                "{ " +
                "\"range\": " +
                "{" +
                "\"field\": \"" + histogram.getField() + "\", " +
                "\"missing\": 0," +
                "\"ranges\": [" + ranges + "]" +
                "}, " +
                parseChildAggregator(histogram.getChilds()) +
                "}";

        return json;
    }

    private String parseChildAggregator(List<Aggregator> aggregators) {
        StringBuilder builder = new StringBuilder("\"aggs\": {");

        for (int i = 0; i < aggregators.size(); i++) {
            Aggregator aggregator = aggregators.get(i);
            if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

            if (i > 0) builder.append(",");
            if (aggregator instanceof Histogram) {
                builder.append(parseHistogramAggregator(aggregator));
            } else if (aggregator instanceof Sum) {
                builder.append(parseSumAggregator(aggregator));
            } else if (aggregator instanceof Avg) {
                builder.append(parseAvgAggregator(aggregator));
            } else if (aggregator instanceof Filters) {
                builder.append(parseFiltersAggregator(aggregator));
            } else if (aggregator instanceof Percentiles) {
                builder.append(parsePercentilesAggregator(aggregator));
            } else throw new RuntimeException("不支持的聚合类型: " + JSON.toJSONString(aggregator));
        }

        builder.append("}");

        return builder.toString();
    }

    private String parseAvgAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Avg avg = (Avg)aggregator;

        String result = "\"" + avg.getField() + "\": {";
        result += "\"avg\": {\"field\": \"" + avg.getField() + "\"}}";
        return result;
    }

    private String parseSumAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Sum sum = (Sum)aggregator;

        String result = "\"" + sum.getField() + "\": {";
        result += "\"sum\": {\"field\": \"" + sum.getField() + "\"}}";
        return result;
    }

    /**
     * The filtered buckets are returned in the same order as provided in the request.
     * @param aggregator
     * @return
     */
    private String parseFiltersAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Filters filters = (Filters) aggregator;

        String result = "\"filters\": {\"filters\" : {\"filters\" : [ ";
        for (int i = 0; i < filters.getFilters().size(); i ++) {
            if (i > 0) result += ",";
            result += parseFilter(filters.getFilters().get(i));
        }
        result += "]}}";

        return result;
    }

    private String parseMinAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Min min = (Min) aggregator;

        String result = "\"" + min.getField() + "\": {";
        result += "\"min\": {\"field\": \"" + min.getField() + "\"}}";
        return result;
    }

    private String parseCardinalityAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Cardinality cardinality = (Cardinality) aggregator;

        String result = "\"" + cardinality.getField() + "\": {";
        result += "\"cardinality\": {\"field\": \"" +
                cardinality.getField() + (cardinality.getFieldType() == FieldType.TEXT ? ".keyword" : "") +
                "\"}}";
        return result;
    }

    private String parsePercentilesAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Percentiles percentiles = (Percentiles) aggregator;

        String result = "\"" + percentiles.getField() + "Percentiles\": {";
        result += "\"percentiles\": {\"field\": \"" + percentiles.getField() + "\"" +
                ((percentiles.getPercents() != null && !percentiles.getPercents().isEmpty()) ? ",\"percents\":" + JSON.toJSONString(percentiles.getPercents()) : "")
                + "}}";
        return result;
    }

    private String parseScriptFilterAggregator(Aggregator aggregator) {
        if (aggregator.isInvalid()) throw new RuntimeException("无效的aggregator: " + JSON.toJSONString(aggregator));

        Script script = (Script) aggregator;

        String result = "\"" + script.getName() + "\": {";
        result += "\"filter\":{\"script\":{\"script\": \"" + script.getScript() + "\"}}";
        result += "}";
        return result;
    }
}