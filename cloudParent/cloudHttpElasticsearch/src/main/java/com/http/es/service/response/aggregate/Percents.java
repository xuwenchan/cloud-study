package com.http.es.service.response.aggregate;

import com.alibaba.fastjson.JSONObject;
import com.http.es.service.response.Aggregate;

public class Percents extends Aggregate {
    JSONObject values;

    public Percents(String name, JSONObject values) {
        super(name);
        this.values = values;
    }

    public JSONObject getValues() {
        return values;
    }

    public Number getValue(String key) {
        return values.getFloatValue(key);
    }
}
