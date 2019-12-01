package com.briup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.briup.**")
public class CmsJd1911Application {

	public static void main(String[] args) {
		SpringApplication.run(CmsJd1911Application.class, args);
	}

}
