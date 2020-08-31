package com.cccloud.blogs;

import com.alibaba.fastjson.JSONObject;
import com.cccloud.blogs.config.feign.baidu.BaiduJsonMessage;
import com.cccloud.blogs.config.feign.baidu.dto.IpLocationDto;
import com.cccloud.blogs.open.baidu.BaiduMapRpc;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan
class BlogsApplicationTests {

	@Autowired
	private BaiduMapRpc baiduMapRpc;

	@Test
	void contextLoads() {
		BaiduJsonMessage<IpLocationDto> message = baiduMapRpc.ipLocation("49.211.109.237");
		System.out.println(message.getStatus());
		System.out.println(JSONObject.toJSONString(message.getContent().getAddressDetail()));
	}

}
