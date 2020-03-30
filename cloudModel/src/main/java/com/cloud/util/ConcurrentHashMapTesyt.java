package com.cloud.util;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTesyt {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> concurrentHashMap=new ConcurrentHashMap<String,Integer>(16);

        concurrentHashMap.put("1",1);


    }
}
