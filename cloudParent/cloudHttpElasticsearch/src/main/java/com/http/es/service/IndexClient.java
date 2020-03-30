package com.http.es.service;

import com.alibaba.fastjson.serializer.PropertyFilter;

public interface IndexClient {
    boolean index(String appname, Object obj);
    boolean index(String appname, Object obj, PropertyFilter propertyFilter);
    boolean index(String appname, String id, Object obj);
    boolean index(String appname, String id, Object obj, PropertyFilter propertyFilter);

    boolean update(String appname, String query, String doc);

    boolean remove(String appname, String id);
}
