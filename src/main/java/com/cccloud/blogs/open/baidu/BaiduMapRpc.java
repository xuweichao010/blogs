package com.cccloud.blogs.open.baidu;

import com.cccloud.blogs.feign.Feign;
import com.cccloud.blogs.open.baidu.commmons.BaiduInterceptor;
import com.cccloud.blogs.open.baidu.commmons.BaiduJsonMessage;
import com.cccloud.blogs.open.baidu.dto.IpLocationDto;
import feign.Param;
import feign.RequestLine;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:27
 * 描述：百度地图API接口
 */
@Feign(url = "${rpc.baidu.map.url}", interceptor = BaiduInterceptor.class)
public interface BaiduMapRpc {

    @RequestLine("GET /location/ip?ip={ip}&coor=bd09ll")
    BaiduJsonMessage<IpLocationDto> ipLocation(@Param("ip") String ip);
}
