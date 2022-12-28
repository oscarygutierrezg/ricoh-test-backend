package com.ricoh.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan("com.ricoh.test")
public class TestBackendRicohApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBackendRicohApplication.class, args);
	}
}
