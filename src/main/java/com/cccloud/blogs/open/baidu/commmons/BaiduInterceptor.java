package com.cccloud.blogs.open.baidu.commmons;

import com.cccloud.blogs.commons.exceptions.SystemException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：CC
 * 时间：2020/9/2 9:35
 * 描述：
 */
public class BaiduInterceptor implements RequestInterceptor {
    @Autowired
    private BaiduMapProperties baiduMapProperties;

    @Override
    public void apply(RequestTemplate template) {
        template.query(BaiduMapProperties.AK, baiduMapProperties.getAk());
        template.query("output", "json");
        Map paramsMap = new LinkedHashMap<String, String>();
        for (Map.Entry<String, Collection<String>> pair : template.queries().entrySet()) {
            String value = pair.getValue().iterator().next();
            paramsMap.put(pair.getKey(), value);
        }
        try {
            String paramsStr = toQueryString(paramsMap);
            String wholeStr = getUri(template) + "?" + paramsStr + baiduMapProperties.getSk();
            String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
            String sn = MD5(tempStr);
            template.query(BaiduMapProperties.SN, sn);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    public String getUri(RequestTemplate template) {
        return template.url().substring(0, template.url().indexOf("?"));
    }

    public String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }


    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
