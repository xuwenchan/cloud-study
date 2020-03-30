package com.http.es.service.response.aggregate;


import org.apache.commons.lang3.StringUtils;
import request.Aggregator;

public class Avg implements Aggregator {
    private String field;

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field);
    }

    public String getField() {
        return field;
    }

    public Avg(String field) {
        this.field = field;
    }
}
