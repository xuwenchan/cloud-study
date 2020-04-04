package com.rabbion.rule;

import com.netflix.loadbalancer.IRule;

public class MyIRule {
    public IRule myIRule() {
        return new FiveRule();
    }
}
