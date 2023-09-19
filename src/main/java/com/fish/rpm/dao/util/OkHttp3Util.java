package com.fish.rpm.dao.util;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttp3Util {
    private static final Logger logger = LoggerFactory.getLogger(OkHttp3Util.class);

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)   // 连接超时时间
            .readTimeout(30, TimeUnit.SECONDS)      // 读取超时时间
            .writeTimeout(30, TimeUnit.SECONDS)     // 写入超时时间
            .build();

    public static Headers buildHeader(Map<String, String> headerMap) {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headerMap == null || headerMap.isEmpty()) {
            return headersBuilder.build();
        }
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            headersBuilder.add(entry.getKey(), entry.getValue());
        }
        return headersBuilder.build();
    }

    /**
     * Get
     */
    public static String get(String url) {
        return getParam(url, new HashMap<>());
    }

    public static String getParam(String url, Map<String, Object> paramMap) {
        return getParamWithHeader(url, paramMap, null);
    }

    public static String getParamWithHeader(String url, Map<String, Object> paramMap, Map<String, String> headerMap){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (String key : paramMap.keySet()) {
            builder.queryParam(key, paramMap.get(key));
        }
        Headers headers = buildHeader(headerMap);
        Request request = new Request.Builder()
                .url(builder.toUriString())
                .headers(headers)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(response.isSuccessful() && response.body() != null){
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("Get Param Request Error:", e);
        }
        return null;
    }

    /**
     * Post Json
     */
    public static String post(String url) {
        return postJson(url, "");
    }

    public static String postJson(String url, String jsonStr) {
        return postJsonWithHeader(url, jsonStr, null);
    }

    public static String postJsonWithHeader(String url, String jsonStr, Map<String, String> headerMap) {
        RequestBody body = RequestBody.create(jsonStr, Constants.JSON_TYPE);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(buildHeader(headerMap))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(response.isSuccessful() && response.body() != null){
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("Post Json Request Error:", e);
        }
        return null;
    }

    /**
     * Post Form
     */
    public static String postForm(String url, Map<String, String> data) {
        return postFormWithHeader(url, data, null);
    }

    public static String postFormWithHeader(String url, Map<String, String> data, Map<String, String> headerMap) {
        FormBody.Builder builder = new FormBody.Builder();
        if (data != null && !data.isEmpty()) {
            for (String key : data.keySet()) {
                builder.add(key, data.get(key));
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .headers(buildHeader(headerMap))
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(response.isSuccessful() && response.body() != null){
                return response.body().string();
            }
        } catch (IOException e) {
            logger.error("Post Form Request Error:", e);
        }
        return null;
    }

}

