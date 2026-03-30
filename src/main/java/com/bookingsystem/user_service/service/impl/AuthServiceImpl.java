package com.bookingsystem.user_service.service.impl;

import com.bookingsystem.user_service.dto.request.LoginRequest;
import com.bookingsystem.user_service.dto.request.RegisterRequest;
import com.bookingsystem.user_service.dto.response.AuthResponse;
import com.bookingsystem.user_service.entity.User;
import com.bookingsystem.user_service.repository.UserRepository;
import com.bookingsystem.user_service.service.AuthService;
import com.bookingsystem.user_service.util.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        userRepository.save(user);

        String token = jwtService.generateToken(user.getId(), user.getRole());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getId(), user.getRole());

        return new AuthResponse(token);
    }
}