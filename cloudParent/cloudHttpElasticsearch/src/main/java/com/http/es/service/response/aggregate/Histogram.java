package com.http.es.service.response.aggregate;

import org.apache.commons.lang3.StringUtils;
import request.Aggregator;
import request.FieldType;

import java.util.ArrayList;
import java.util.List;

public class Histogram implements Aggregator {
    private String field;
    private FieldType type = FieldType.TEXT;

    private Number interval = 0;
    private Number offset = 0;
    private Number min = 0;
    private Number max = 0;

    private List<? extends Number> delims = new ArrayList<>();
    private List<String> customNames = new ArrayList<>();

    private List<Aggregator> childs = new ArrayList<>();

    public String getField() {
        return field;
    }

    public FieldType getType() {
        return type;
    }

    public Number getInterval() {
        return interval;
    }

    public Number getOffset() {
        return offset;
    }

    public void setInterval(Number interval) {
        this.interval = interval;
    }

    public Number getMin() {
        return min;
    }

    public void setMin(Number min) {
        this.min = min;
    }

    public Number getMax() {
        return max;
    }

    public void setMax(Number max) {
        this.max = max;
    }

    public List<? extends Number> getDelims() {
        return delims;
    }

    public void setDelims(List<? extends Number> delims) {
        this.delims = delims;
    }

    public List<String> getCustomNames() {
        return customNames;
    }

    public void setCustomNames(List<String> customNames) {
        this.customNames = customNames;
    }

    public List<Aggregator> getChilds() {
        return childs;
    }

    public void setChilds(List<Aggregator> childs) {
        this.childs = childs;
    }

    public void addChild(Aggregator child) {
        this.childs.add(child);
    }

    public Histogram(String field) {
        this.field = field;
    }

    public Histogram(String field, FieldType type) {
        this.field = field;
        this.type = type;
    }

    public Histogram(String field, Number interval, Number min, Number max) {
        this.field = field;
        this.interval = interval;
        this.min = min;
        this.max = max;
    }

    public Histogram(String field, Number interval, Number offset, Number min, Number max) {
        this.field = field;
        this.interval = interval;
        this.offset = offset;
        this.min = min;
        this.max = max;
    }

    public Histogram(String field, List<? extends Number> delims, List<String> customNames) {
        this.field = field;
        this.type = FieldType.NUMBER;
        this.delims = delims;
        this.customNames = customNames;
    }

    public Histogram(String field, FieldType type, List<Aggregator> childs) {
        this.field = field;
        this.type = type;
        this.childs = childs;
    }

    public Histogram(String field, List<? extends Number> delims, List<String> customNames, List<Aggregator> childs) {
        this.field = field;
        this.type = FieldType.NUMBER;
        this.delims = delims;
        this.customNames = customNames;
        this.childs = childs;
    }

    @Override
    public boolean isInvalid() {
        if (StringUtils.isBlank(field)) return true;
        for (Aggregator child : childs) {
            if (child.isInvalid()) return true;
        }
        if ((delims == null || delims.isEmpty()) && (customNames == null || customNames.isEmpty())) return false;
        if (delims != null && !delims.isEmpty() && customNames != null && !customNames.isEmpty() && customNames.size() - delims.size() == 1) return false;
        return true;
    }
}
