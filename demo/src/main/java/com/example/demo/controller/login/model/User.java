package com.example.demo.controller.login.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Table(name = "users")
public class User {
    public int seq;
    public String userId;
    public String password;
}
