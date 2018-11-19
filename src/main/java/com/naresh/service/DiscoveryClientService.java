package com.naresh.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class DiscoveryClientService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "reliable")
    public String readHello(){
        URI uri=URI.create("http://localhost:8082/discovery-client/");
        return restTemplate.getForObject(uri,String.class);
    }

    public String reliable() {
        return "### Fallback method executed ###";
    }
}
