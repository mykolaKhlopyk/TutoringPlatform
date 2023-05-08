package com.mkh.tutoringplatform.service.impl;

import com.mkh.tutoringplatform.service.AuthService;
import com.mkh.tutoringplatform.web.dto.auth.JwtRequest;
import com.mkh.tutoringplatform.web.dto.auth.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponse login(JwtRequest jwtRequest) {
        return null;
    }

    @Override
    public JwtRequest refresh(String refreshToken) {
        return null;
    }
}
