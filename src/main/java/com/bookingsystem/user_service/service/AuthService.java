package com.bookingsystem.user_service.service;

import com.bookingsystem.user_service.dto.request.LoginRequest;
import com.bookingsystem.user_service.dto.request.RegisterRequest;
import com.bookingsystem.user_service.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
