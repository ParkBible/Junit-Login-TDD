package com.example.demo.controller.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.login.dto.LoginRequest;
import com.example.demo.controller.login.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
    final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Integer> login(LoginRequest loginRequest) {
        System.out.println("로그인을 합니다.");
        return loginService.login(loginRequest);
    }
}
