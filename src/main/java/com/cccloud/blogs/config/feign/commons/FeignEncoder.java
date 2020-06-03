package com.cccloud.blogs.config.feign.commons;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.springframework.stereotype.Component;


/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:27
 * 描述：Feign 远程调用封装类
 */
@Component
public class FeignEncoder implements Encoder {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Override
    public void encode(Object requestBody, Type bodyType, RequestTemplate request)
            throws EncodeException {
        // template.body(conversionService.convert(object, String.class));
        if (requestBody != null) {
            Class<?> requestType = requestBody.getClass();
            Collection<String> contentTypes = request.headers().get("Content-Type");

            MediaType requestContentType = null;
            if (contentTypes != null && !contentTypes.isEmpty()) {
                String type = contentTypes.iterator().next();
                requestContentType = MediaType.valueOf(type);
            }

            for (HttpMessageConverter<?> messageConverter : this.messageConverters
                    .getObject().getConverters()) {
                if (messageConverter.canWrite(requestType, requestContentType)) {

                    FeignOutputMessage outputMessage = new FeignOutputMessage(request);
                    try {
                        @SuppressWarnings("unchecked")
                        HttpMessageConverter<Object> copy = (HttpMessageConverter<Object>) messageConverter;
                        copy.write(requestBody, requestContentType, outputMessage);
                    } catch (IOException ex) {
                        throw new EncodeException("Error converting request body", ex);
                    }
                    request.headers(null);
                    request.headers(FeignUtils.getHeaders(outputMessage.getHeaders()));

                    // do not use charset for binary data
                    if (messageConverter instanceof ByteArrayHttpMessageConverter) {
                        request.body(outputMessage.getOutputStream().toByteArray(), null);
                    } else {
                        request.body(outputMessage.getOutputStream().toByteArray(), StandardCharsets.UTF_8);
                    }
                    return;
                }
            }
            String message = "Could not write request: no suitable HttpMessageConverter "
                    + "found for request type [" + requestType.getName() + "]";
            if (requestContentType != null) {
                message += " and content type [" + requestContentType + "]";
            }
            throw new EncodeException(message);
        }
    }

    private static class FeignOutputMessage implements HttpOutputMessage {

        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        private final HttpHeaders httpHeaders;

        private FeignOutputMessage(RequestTemplate request) {
            httpHeaders = FeignUtils.getHttpHeaders(request.headers());
        }

        @Override
        public OutputStream getBody() throws IOException {
            return this.outputStream;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.httpHeaders;
        }

        public ByteArrayOutputStream getOutputStream() {
            return this.outputStream;
        }

    }
}
