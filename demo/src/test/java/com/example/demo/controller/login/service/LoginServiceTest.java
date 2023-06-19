package com.example.demo.controller.login.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.login.dto.LoginRequest;
import com.example.demo.controller.login.model.User;
import com.example.demo.controller.login.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private UserRepository userRepository;

    @Nested
    class 아이디가_빈_값이라면 {
        @Test
        void 상태코드_400을_리턴한다() {
            //given
            LoginRequest loginRequest = new LoginRequest("", "testPassword");
            
            //when
            ResponseEntity<Integer> result = loginService.login(loginRequest);

            //then
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        }
    }

    @Nested
    class 패스워드가_빈_값이라면 {
        @Test
        void 상태코드_400을_리턴한다() {
            //given
            LoginRequest loginRequest = new LoginRequest("testId", "");
            
            //when
            ResponseEntity<Integer> result = loginService.login(loginRequest);

            //then
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        }
    }

    @Nested
    class 아이디가_맞지_않다면 {
        @Test
        void 상태코드_401을_리턴한다() {
            //given
            LoginRequest loginRequest = new LoginRequest("wrongId", "testPassword");
            when(userRepository.findByUserId(loginRequest.userId)).thenReturn(Optional.ofNullable(null));
            
            //when
            ResponseEntity<Integer> result = loginService.login(loginRequest);

            //then
            assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        }
    }

    @Nested
    class 비밀번호가_맞지_않다면 {
        @Test
        void 상태코드_401을_리턴한다() {
            //given
            LoginRequest loginRequest = new LoginRequest("testId", "wrongPassword");
            when(userRepository.findByUserId(loginRequest.userId)).thenReturn(Optional.of(new User(1, "testId", "testPassword")));
            
            //when
            ResponseEntity<Integer> result = loginService.login(loginRequest);

            //then
            assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        }
    }

    @Nested
    class 비밀번호가_맞다면 {
        @Test
        void 상태코드_200과_유저_시퀀스를_리턴한다() {
            //given
            LoginRequest loginRequest = new LoginRequest("testId", "testPassword");
            when(userRepository.findByUserId(loginRequest.userId)).thenReturn(Optional.of(new User(1, "testId", "testPassword")));
            
            //when
            ResponseEntity<Integer> result = loginService.login(loginRequest);

            //then
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(1, result.getBody());
        }
    }
}
