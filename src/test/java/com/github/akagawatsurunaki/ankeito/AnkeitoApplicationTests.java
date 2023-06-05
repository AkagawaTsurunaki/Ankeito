package com.github.akagawatsurunaki.ankeito;

import com.alibaba.fastjson2.JSONObject;
import com.github.akagawatsurunaki.ankeito.service.UserService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnkeitoApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

}
