package com.policy.selection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.policy.selection.service.impl")
public class MicroserevicesInsurancePolicyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserevicesInsurancePolicyApplication.class, args);
	}

}
