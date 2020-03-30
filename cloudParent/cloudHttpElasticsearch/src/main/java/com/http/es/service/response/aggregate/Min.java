package com.http.es.service.response.aggregate;


import org.apache.commons.lang3.StringUtils;
import request.Aggregator;

public class Min implements Aggregator {
    private String field;

    public Min(String field) {
        this.field = field;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field);
    }

    public String getField() {
        return field;
    }
}
