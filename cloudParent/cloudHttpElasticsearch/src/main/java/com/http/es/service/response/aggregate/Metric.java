package com.http.es.service.response.aggregate;


import com.http.es.service.response.Aggregate;

public class Metric extends Aggregate {
    private Number value;

    public Metric(String name, Number value) {
        super(name);
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
