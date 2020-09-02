package com.cccloud.blogs;

import com.alibaba.fastjson.JSONObject;
import com.cccloud.blogs.open.baidu.BaiduMapRpc;
import com.cccloud.blogs.open.baidu.commmons.BaiduJsonMessage;
import com.cccloud.blogs.open.baidu.dto.IpLocationDto;
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
