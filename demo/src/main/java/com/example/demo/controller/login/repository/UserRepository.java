package com.example.demo.controller.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.controller.login.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUserId(String userId);
}
