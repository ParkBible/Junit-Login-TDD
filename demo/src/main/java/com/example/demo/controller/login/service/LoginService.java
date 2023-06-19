package com.example.demo.controller.login.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.controller.login.dto.LoginRequest;
import com.example.demo.controller.login.model.User;
import com.example.demo.controller.login.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    public ResponseEntity<Integer> login(LoginRequest loginRequest) {
        if (!isRequestValid(loginRequest)) {
            return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
        }

        Optional<User> foundUser = userRepository.findByUserId(loginRequest.userId);

        if (foundUser.isPresent()) {
            User user = foundUser.get();
            
            if (loginRequest.password == user.password) {
                return new ResponseEntity<Integer>(user.seq, HttpStatus.OK);
            }
        }

        return new ResponseEntity<Integer>(0, HttpStatus.UNAUTHORIZED);
    }

    private boolean isRequestValid(LoginRequest loginRequest) {
        return !(loginRequest.userId.isBlank() || loginRequest.password.isBlank());
    }
}
