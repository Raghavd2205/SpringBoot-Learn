package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dto.LoginRequestDTO;
import com.SpringBootLearn.demo.dto.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO request);
}
