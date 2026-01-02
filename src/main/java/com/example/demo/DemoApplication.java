package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.*;
import java.io.IOException;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	private static final String FILE_PATH = "/data/message.txt";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
		String podName = System.getenv("POD_NAME");
        String podIp = System.getenv("POD_IP");

        String message = "No message stored yet";

        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                message = Files.readString(Paths.get(FILE_PATH));
            }
        } catch (IOException e) {
            message = "Error reading file";
        }

        return "Message: " + message +
               " | Pod: " + podName +
               " | IP: " + podIp;
    }
	
	
	@GetMapping("/store/{msg}")
    public String storeMessage(@PathVariable String msg) {
        try {
            Files.writeString(
                Paths.get(FILE_PATH),
                msg,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );
            return "Message stored successfully: " + msg;
        } catch (IOException e) {
            return "Error storing message";
        }
    }
	
	
}
