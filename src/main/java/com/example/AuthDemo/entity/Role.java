package com.example.AuthDemo.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    USER, ADMIN
}
