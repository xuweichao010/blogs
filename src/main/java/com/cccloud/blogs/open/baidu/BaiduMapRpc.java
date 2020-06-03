package com.cccloud.blogs.open.baidu;

import feign.Param;
import feign.RequestLine;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:27
 * 描述：百度地址API接口
 */
public interface BaiduMapRpc {

    @RequestLine("GET /location/ip?ip={ip}&coor=bd09ll")
    Object ipLocation(@Param("ip") String ip);
}
