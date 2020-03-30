package com.http.es.service.impl;

import client.HttpClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.http.es.service.RequestParser;
import com.http.es.service.SearchClient;
import com.http.es.service.response.ResponseExtractor;
import com.http.es.service.response.SearchResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;
import request.SearchRequest;

import java.io.IOException;
import java.util.Base64;

public class EsSearchClient implements SearchClient {
    private RequestParser requestParser = new EsRequestParser();
    private ResponseExtractor responseExtractor = new EsResponseExtractor();

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
    public SearchResponse search(SearchRequest request) {
        try {
            String query = requestParser.parse(request);
            JSONObject jsonResponse = JSON.parseObject(httpClient.executePostMethod(server + request.getAppname() + "/_search?typed_keys", query, new Header[]{
                    new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"),
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"),
                    new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
            }));
            return responseExtractor.extract(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询异常！", e);
        }
    }

    @Override
    public SearchResponse search(SearchRequest request, int timeout) {
        try {
            String query = requestParser.parse(request);
            JSONObject jsonResponse = JSON.parseObject(httpClient.executePostMethod(server + request.getAppname() + "/_search?typed_keys", query, new Header[]{
                    new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"),
                    new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"),
                    new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
            }, timeout));
            return responseExtractor.extract(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询异常!", e);
        }
    }

    public void destroy() throws IOException {
        if (httpClient != null) httpClient.close();
    }
}
