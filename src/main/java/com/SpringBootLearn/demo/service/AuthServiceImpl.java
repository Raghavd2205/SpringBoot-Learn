package com.SpringBootLearn.demo.service;

import com.SpringBootLearn.demo.dao.UserRepository;
import com.SpringBootLearn.demo.dto.LoginRequestDTO;
import com.SpringBootLearn.demo.dto.LoginResponseDTO;
import com.SpringBootLearn.demo.entity.User;
import com.SpringBootLearn.demo.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserRepository repo;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = this.repo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponseDTO(token);
    }
}
