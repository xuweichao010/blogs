package com.cccloud.blogs;

import com.alibaba.fastjson.JSONObject;
import com.cccloud.blogs.open.baidu.BaiduMapRpc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogsApplicationTests {

	@Autowired
	private BaiduMapRpc baiduMapRpc;

	@Test
	void contextLoads() {
		Object o = baiduMapRpc.ipLocation("49.211.109.237");
		System.out.println(JSONObject.toJSONString(o));

	}

}
