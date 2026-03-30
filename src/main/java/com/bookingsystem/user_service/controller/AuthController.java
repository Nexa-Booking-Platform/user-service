package com.bookingsystem.user_service.controller;

import com.bookingsystem.user_service.dto.request.LoginRequest;
import com.bookingsystem.user_service.dto.request.RegisterRequest;
import com.bookingsystem.user_service.dto.response.AuthResponse;
import com.bookingsystem.user_service.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}