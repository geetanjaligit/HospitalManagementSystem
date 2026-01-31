package com.project.hospitalManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospitalManagement.dto.LoginRequestDto;
import com.project.hospitalManagement.dto.LoginResponseDto;
import com.project.hospitalManagement.dto.SignupRequestDto;
import com.project.hospitalManagement.dto.SignupResponseDto;
import com.project.hospitalManagement.security.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto)
    {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
