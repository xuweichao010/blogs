package com.cccloud.blogs.config.feign.commons;


import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;


/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:27
 * 描述：Feign 远程调用解析类
 */
@Component
public class FeignDecoder implements Decoder {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if (type instanceof Class || type instanceof ParameterizedType
                || type instanceof WildcardType) {
            @SuppressWarnings({"unchecked", "rawtypes"})
            HttpMessageConverterExtractor<?> extractor = new HttpMessageConverterExtractor(
                    type, this.messageConverters.getObject().getConverters());

            return extractor.extractData(new FeignResponseAdapter(response));
        }
        throw new DecodeException(response.status(), String.format("%s is not a type supported by this decoder.", type), response.request());
    }

    private class FeignResponseAdapter implements ClientHttpResponse {

        private final Response response;

        private FeignResponseAdapter(Response response) {
            this.response = response;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.valueOf(this.response.status());
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.status();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.reason();
        }

        @Override
        public void close() {
            try {
                this.response.body().close();
            } catch (IOException ex) {
            }
        }
        @Override
        public InputStream getBody() throws IOException {
            return this.response.body().asInputStream();
        }
        @Override
        public HttpHeaders getHeaders() {
            return FeignUtils.getHttpHeaders(this.response.headers());
        }

    }
}
