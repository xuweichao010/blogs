package com.cccloud.blogs.open.baidu.commmons;

import lombok.Data;


/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:27
 * 描述：百度地址API授权信息
 */
@Data
public class BaiduMapProperties {

    public static final String AK = "ak";

    public static final String SK = "sk";

    public static final String SN = "sn";

    private String ak;
    private String sk;
    private String url = "http://api.map.baidu.com";
}
