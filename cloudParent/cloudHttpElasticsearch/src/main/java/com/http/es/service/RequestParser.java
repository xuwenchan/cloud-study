package com.http.es.service;

import request.SearchRequest;

public interface RequestParser {
    String parse(SearchRequest request);
}
