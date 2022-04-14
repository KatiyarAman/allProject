package com.db.multi.utils;

import org.json.simple.JSONObject;

import java.util.Map;

public interface ApiManagerI {
    public String getApi(String url, Map<String,String> queryParam, Boolean isCompleted);
    public String postApi(String url, JSONObject jsonObject);
}
