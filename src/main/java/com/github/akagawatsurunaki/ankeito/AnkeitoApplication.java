package com.github.akagawatsurunaki.ankeito;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.akagawatsurunaki.ankeito.dao")
public class AnkeitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnkeitoApplication.class, args);
	}

}
