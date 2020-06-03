package com.cccloud.blogs.config.feign.baidu;

import lombok.Data;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 20:11
 * 描述：
 */
@Data
public class BaiduJsonMessage<T> {
    Integer status;
    T content;
    String address;
}
