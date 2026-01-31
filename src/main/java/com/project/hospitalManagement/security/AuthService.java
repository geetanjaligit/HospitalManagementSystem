package com.project.hospitalManagement.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.hospitalManagement.dto.LoginRequestDto;
import com.project.hospitalManagement.dto.LoginResponseDto;
import com.project.hospitalManagement.dto.SignupRequestDto;
import com.project.hospitalManagement.dto.SignupResponseDto;
import com.project.hospitalManagement.entity.User;
import com.project.hospitalManagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        
        Authentication authentication=authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user=(User)authentication.getPrincipal();
        String token=authUtil.generateAccessToken(user);
        return new LoginResponseDto(token,user.getId());
    }

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        User user=userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if(user!=null)
        {
            throw new RuntimeException("User already exists");
        }
        user=userRepository.save(User.builder()
            .username(signupRequestDto.getUsername())
            .password(passwordEncoder.encode(signupRequestDto.getPassword()))
            .build()
        );
        return new SignupResponseDto(user.getId(),user.getUsername());
    }

}
