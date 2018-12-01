package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 * @author chenxy
 */
@SpringBootApplication
public class SpringBootWebApplication {

	/**
	 * 除去warning扫描
	 */
	@org.apache.ibatis.annotations.Mapper
	public interface NoWarnMapper{}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
}



