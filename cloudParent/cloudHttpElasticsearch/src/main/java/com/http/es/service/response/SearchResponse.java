package com.http.es.service.response;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private int total = 0;
    private List<Record> records = new ArrayList<>();
    private List<Aggregate> aggregates = new ArrayList<>();


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Aggregate> getAggregates() {
        return aggregates;
    }

    public void setAggregates(List<Aggregate> aggregates) {
        this.aggregates = aggregates;
    }

    public <T extends Aggregate> T getAggregate(String name, Class<T> clazz) {
        for (Aggregate aggregate : aggregates) {
            if (aggregate.getName().equals(name)) return (T) aggregate;
        }

        try {
            Aggregate aggregate = clazz.newInstance();
            aggregate.setName(name);

            return (T) aggregate;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
