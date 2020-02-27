package com.kason.hello.spring.cloud.web.admin.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    private String url ="http://HELLO-SPRING-CLOUD-SERVICE-ADMIN/";

    @HystrixCommand(fallbackMethod = "ErrorHystrix")
    public String sayHi(String message) {
        return restTemplate.getForObject(url+"hi?message=" + message, String.class);
    }

    public String ErrorHystrix(String message){
        return String.format("Hi,your message is : %s but request error",message);
    }
}
