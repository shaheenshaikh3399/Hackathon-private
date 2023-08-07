package com.policy;

import com.policy.repositories.PolicyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PolicyServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PolicyServiceApplication.class, args);
	}

	@Autowired
	private PolicyRepo policyRepo;
	@Override
	public void run(String... args) throws Exception {

	}
}
