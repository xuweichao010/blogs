package com.cccloud.blogs.config.feign.commons;

import org.springframework.http.HttpHeaders;

import java.util.*;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:41
 * 描述：
 */
public class FeignUtils {
    static HttpHeaders getHttpHeaders(Map<String, Collection<String>> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Map.Entry<String, Collection<String>> entry : headers.entrySet()) {
            httpHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return httpHeaders;
    }

    static Map<String, Collection<String>> getHeaders(HttpHeaders httpHeaders) {
        LinkedHashMap<String, Collection<String>> headers = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            headers.put(entry.getKey(), entry.getValue());
        }

        return headers;
    }
}
