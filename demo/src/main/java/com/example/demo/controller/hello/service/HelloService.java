package com.example.demo.controller.hello.service;

import org.springframework.stereotype.Service;
import com.example.demo.controller.hello.dto.HelloDto;

@Service
public class HelloService {
    public String hello(HelloDto helloDto) {
        return "Hello";
    }
}
