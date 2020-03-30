package com.http.es.service.response.aggregate;

import request.Aggregator;
import request.Filter;

import java.util.ArrayList;
import java.util.List;

public class Filters implements Aggregator {
    private List<Filter> filters = new ArrayList<>();

    public Filters() {}

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    @Override
    public boolean isInvalid() {
        for (Filter filter : filters)
            if (filter.isInvalid()) return true;

        return false;
    }

    public List<Filter> getFilters() {
        return filters;
    }
}
