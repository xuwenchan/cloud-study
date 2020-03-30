package com.http.es.service;


import com.http.es.service.response.SearchResponse;
import request.SearchRequest;

public interface SearchClient {
    SearchResponse search(SearchRequest request);
    SearchResponse search(SearchRequest request, int timeout);
}
