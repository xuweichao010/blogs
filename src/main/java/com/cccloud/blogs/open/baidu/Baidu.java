package com.cccloud.blogs.open.baidu;

import com.alibaba.fastjson.JSONObject;
import com.cccloud.blogs.open.baidu.dto.IpLocationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作者：CC
 * 时间：2020/9/2 8:51
 * 描述：
 */
//@Component
//@Slf4j
//public class Baidu implements InitializingBean {
////
//}    @Autowired
////    private BaiduMapRpc baiduMapRpc;
////
////    @Override
////    public void afterPropertiesSet() throws Exception {
////        System.out.println("-------------------------------------------");
////        System.out.println(baiduMapRpc);
////        log.debug("111:{}",baiduMapRpc);
////        IpLocationDto dispose = baiduMapRpc.ipLocation("113.57.176.13").dispose();
////        System.out.println(JSONObject.toJSONString(dispose));
////    }
