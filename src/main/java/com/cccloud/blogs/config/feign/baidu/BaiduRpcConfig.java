package com.cccloud.blogs.config.feign.baidu;

import com.cccloud.blogs.commons.exceptions.SystemException;
import com.cccloud.blogs.config.feign.commons.FeignConfig;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:27
 * 描述：百度RPC远程调用配置
 */
@Configuration
public class BaiduRpcConfig {

    @Autowired
    private FeignConfig feignConfig;

//    @Bean
//    public BaiduMapRpc baiduMapRpc() {
//        return feignConfig.buildRpc(BaiduMapRpc.class, baiduMapProperties().getUrl(), baiduInterceptor());
//    }

    @Bean
    @ConfigurationProperties(prefix = "rpc.baidu.map")
    public BaiduMapProperties baiduMapProperties() {
        return new BaiduMapProperties();
    }

    @Bean
    public BaiduInterceptor baiduInterceptor() {
        return new BaiduInterceptor();
    }

    class BaiduInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template) {
            template.header(FeignConfig.HTTP_HEADER_CONTENT_TYPE, "application/json; charset=UTF-8");
            template.header(FeignConfig.HTTP_HEADER_ACCEPT, "application/json");
            template.query(BaiduMapProperties.AK, baiduMapProperties().getAk());
            template.query("output", "json");
            Map paramsMap = new LinkedHashMap<String, String>();
            for (Map.Entry<String, Collection<String>> pair : template.queries().entrySet()) {
                String value = pair.getValue().iterator().next();
                paramsMap.put(pair.getKey(), value);
            }
            try {
                String paramsStr = toQueryString(paramsMap);
                String wholeStr = getUri(template) + "?" + paramsStr + baiduMapProperties().getSk();
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

}
