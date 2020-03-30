package com.http.es.service.response.aggregate;


import org.apache.commons.lang3.StringUtils;
import request.Aggregator;
import request.FieldType;

public class Cardinality implements Aggregator {
    private String field;
    private FieldType fieldType = FieldType.TEXT;

    public Cardinality(String field) {
        this.field = field;
    }

    public Cardinality(String field, FieldType fieldType) {
        this.field = field;
        this.fieldType = fieldType;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(field);
    }

    public String getField() {
        return field;
    }

    public FieldType getFieldType() {
        return fieldType;
    }
}
