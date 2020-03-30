package com.cloud.util;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> concurrentHashMap=new ConcurrentHashMap<String,Integer>();
        concurrentHashMap.put("1",1);
    }
}
