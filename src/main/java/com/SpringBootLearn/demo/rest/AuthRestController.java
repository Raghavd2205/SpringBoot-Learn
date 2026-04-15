package com.SpringBootLearn.demo.rest;

import com.SpringBootLearn.demo.common.ApiResponse;
import com.SpringBootLearn.demo.dto.ExpenseTrackerResponseDTO;
import com.SpringBootLearn.demo.dto.LoginRequestDTO;
import com.SpringBootLearn.demo.dto.LoginResponseDTO;
import com.SpringBootLearn.demo.service.AuthService;
import com.SpringBootLearn.demo.utility.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/login")
    ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest){
        return ResponseUtil.success(this.authService.login(loginRequest),200);
    }
}
