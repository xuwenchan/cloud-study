package com.http.es.service.response.aggregate;


import org.apache.commons.lang3.StringUtils;
import request.Aggregator;
import request.FieldType;

public class Sum implements Aggregator {
    private String field;
    private FieldType type = FieldType.NUMBER;

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field);
    }

    public String getField() {
        return field;
    }

    public FieldType getType() {
        return type;
    }

    public Sum(String field) {
        this.field = field;
    }
}
