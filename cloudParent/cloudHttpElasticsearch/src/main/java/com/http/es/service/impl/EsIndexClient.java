package com.http.es.service.impl;

import client.HttpClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.http.es.service.IndexClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.Base64;

public class EsIndexClient implements IndexClient {
    private HttpClient httpClient;

    private String server;
    private int timeout = 5000;
    private int poolSize = 10;

    private String username;
    private String password;
    private String basicAuth;
    private boolean insecure = false;

    public void setServer(String server) {
        this.server = server;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInsecure(boolean insecure) {
        this.insecure = insecure;
    }

    public void init() {
        if (!server.endsWith("/")) server = server + "/";
        httpClient = new HttpClient(timeout, poolSize);
        httpClient.setInsecure(insecure);
        httpClient.init();

        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            Base64.Encoder encoder = Base64.getEncoder();
            basicAuth = encoder.encodeToString((username + ":" + password).getBytes());
        }
    }


    @Override
    public boolean index(String appname, Object obj) {
        return index(appname, obj, null);
    }

    @Override
    public boolean index(String appname, Object obj, PropertyFilter propertyFilter) {
        String url = server + appname + "/";
        String requestBody = buildIndexData(obj, propertyFilter);

        return doIndex(url, requestBody);
    }

    private String buildIndexData(Object obj, PropertyFilter propertyFilter) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(obj);
        jsonObject.put("ttl", System.currentTimeMillis() + 120 * 60 * 1000);

        return propertyFilter == null ? jsonObject.toJSONString() : JSON.toJSONString(jsonObject, propertyFilter);
    }

    private boolean doIndex(String url, String requestBody) {
        try {
            JSONObject jsonResponse = JSON.parseObject(httpClient.executePostMethod(url, requestBody, new Header[]{
                    new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"),
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"),
                    new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
            }));

            JSONObject shardsInfo = jsonResponse.getJSONObject("_shards");
            int successfulCount = shardsInfo.getInteger("successful");

            return successfulCount > 0;
        } catch (Exception e) {
            throw new RuntimeException("fail to index data: " + requestBody, e);
        }
    }

    @Override
    public boolean index(String appname, String id, Object obj) {
        return index(appname, id, obj, null);
    }

    @Override
    public boolean index(String appname, String id, Object obj, PropertyFilter propertyFilter) {
        String url = server + appname + "/" + id + "/_update";
        String requestBody = "{\"doc\":" + buildIndexData(obj, propertyFilter) + ",\"doc_as_upsert\":true,\"detect_noop\":false}";

        return doIndex(url, requestBody);
    }

    @Override
    public boolean update(String appname, String query, String doc) {
        String url = server + appname + "/_update_by_query?conflicts=proceed";
        String requestBody = "{\"query\":" + query + ",\"script\":" + doc + "}";

        try {
            JSONObject jsonResponse = JSON.parseObject(httpClient.executePostMethod(url, requestBody, new Header[] {new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)}));
            int count = jsonResponse.getInteger("updated");
            return count == 1;
        } catch (Exception e) {
            throw new RuntimeException("fail to update index data: " + appname + "/" + doc, e);
        }
    }

    @Override
    public boolean remove(String appname, String id) {
        if (StringUtils.isBlank(id)) return true; // id为空不做任何操作

        String url = server + appname + "/" + id;
        try {
            JSONObject jsonResponse = JSON.parseObject(httpClient.executeDeleteMethod(url));

            JSONObject shardsInfo = jsonResponse.getJSONObject("_shards");
            int successfulCount = shardsInfo.getInteger("successful");

            return successfulCount > 0;
        } catch (Exception e) {
            throw new RuntimeException("fail to delete " + appname + " data: " + id, e);
        }
    }

    public void destroy() throws IOException {
        if (httpClient != null) httpClient.close();
    }
}
