package com.naresh;

import com.naresh.service.DiscoveryClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@RestController
@SpringBootApplication
public class SpringCloudHystrix {

	@Autowired
	private DiscoveryClientService clientService;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHystrix.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder templateBuilder){
		return templateBuilder.build();
	}

	@GetMapping("/to-read")
	public String readHelloWorld(){
		return clientService.readHello();
	}
}
