package com.example.apiserverspring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @GetMapping("/api/hello")
    public String hello() {
        LocalDateTime now = LocalDateTime.now();
        String ipAddress = getIPAddress();
        String message = "Hello from the server";

        Map<String, String> response = new HashMap<>();
        response.put("ipAddress", ipAddress);
        response.put("date", now.toString());
        response.put("message",message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
    @GetMapping("/getImage")
    public byte[] getImage(){
        File file=new File("C:\\Users\\MY\\Downloads\\atzili.jpg");
        try {
            byte[]image= FileUtils.readFileToByteArray(file);
            System.out.println(image.length);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }
}




