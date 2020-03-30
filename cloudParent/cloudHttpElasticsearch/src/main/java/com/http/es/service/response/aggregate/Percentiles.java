package com.http.es.service.response.aggregate;


import org.apache.commons.lang3.StringUtils;
import request.Aggregator;

import java.util.List;

public class Percentiles implements Aggregator {
    private String field;
    private List<Number> percents;

    public Percentiles(String field) {
        this.field = field;
    }

    public Percentiles(String field, List<Number> percents) {
        this.field = field;
        this.percents = percents;
    }

    public String getField() {
        return field;
    }

    public List<Number> getPercents() {
        return percents;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field);
    }
}
