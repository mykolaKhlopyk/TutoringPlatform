package com.mkh.tutoringplatform.service;

import com.mkh.tutoringplatform.web.dto.auth.JwtRequest;
import com.mkh.tutoringplatform.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest jwtRequest);

    JwtRequest refresh(String refreshToken);

}
