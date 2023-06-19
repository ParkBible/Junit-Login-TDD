package com.example.demo.controller.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.hello.dto.HelloDto;
import com.example.demo.controller.hello.service.HelloService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        HelloDto helloDto = new HelloDto();

        return helloService.hello(helloDto);
    }
}
