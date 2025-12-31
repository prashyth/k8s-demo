package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
		String podName = System.getenv("POD_NAME");
		String podIp = System.getenv("POD_IP");
		String message = System.getenv("WELCOME_MSG");

		return "This is " + message +" | Pod Name: " + podName + " | Pod IP: " + podIp;
    }
}
