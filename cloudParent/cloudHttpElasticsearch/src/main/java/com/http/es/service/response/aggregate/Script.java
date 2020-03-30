package com.http.es.service.response.aggregate;


import org.apache.commons.lang3.StringUtils;
import request.Aggregator;

public class Script implements Aggregator {
    private String name;
    private String script;

    public Script(String name, String script) {
        this.name = name;
        this.script = script;
    }

    public String getName() {
        return name;
    }

    public String getScript() {
        return script;
    }

    @Override
    public boolean isInvalid() {
        return StringUtils.isBlank(name) || StringUtils.isBlank(script);
    }
}
