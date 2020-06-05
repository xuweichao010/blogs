package com.cccloud.blogs.service.system;

import com.cccloud.blogs.config.feign.baidu.BaiduJsonMessage;
import com.cccloud.blogs.config.feign.baidu.dto.IpLocationDto;
import com.cccloud.blogs.entity.system.Access;
import com.cccloud.blogs.mapper.system.AccessMapper;
import com.cccloud.blogs.open.baidu.BaiduMapRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 作者：徐卫超
 * 时间：2020/6/4 8:35
 * 描述：
 */
@Service
public class AccessService {
    @Autowired
    private BaiduMapRpc baiduMapRpc;
    @Autowired
    private AccessMapper accessMapper;

    private Set<String> ignoreIp = new HashSet<>(Arrays.asList("0:0:0:0:0:0:0:1", "127.0.0.1", "localhost"));

    public void anonymous(String ip) {
        //处理非法IP统计
        if (ip == null || ip.isEmpty() || ignoreIp.contains(ip)) return;
        IpLocationDto location = baiduMapRpc.ipLocation(ip).dispose();
        Access convert = Access.convert(location, ip);
        try {
            accessMapper.insert(convert);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
