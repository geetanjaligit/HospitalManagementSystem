package com.project.hospitalManagement.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.hospitalManagement.dto.LoginRequestDto;
import com.project.hospitalManagement.dto.LoginResponseDto;
import com.project.hospitalManagement.dto.SignupRequestDto;
import com.project.hospitalManagement.dto.SignupResponseDto;
import com.project.hospitalManagement.entity.User;
import com.project.hospitalManagement.entity.type.AuthProviderType;
import com.project.hospitalManagement.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }
    public User signUpInternal(LoginRequestDto signupRequestDto, AuthProviderType authProviderType,String providerId)
    {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if (user != null) {
            throw new RuntimeException("User already exists");
        }
        user=User.builder()
               .username(signupRequestDto.getUsername())
               .providerId(providerId)
               .providerType(authProviderType)
               .build();
        if(authProviderType==AuthProviderType.EMAIL)
        {
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }
        return userRepository.save(user);
        
    }
    //controller will call this method
    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user=signUpInternal(signupRequestDto,AuthProviderType.EMAIL,null);
        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        // fetch providerType and providerId from oAuth2User, save the providerType and
        // provider id info with user
        // if the user has an account: directly login
        // else create an account and then login

        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);
        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);

        String email = oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);
        if (user == null && emailUser == null) {
            // signup flow:
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user=signUpInternal(new LoginRequestDto(username, null),providerType,providerId);
        }
        else if(user!=null)
        {
            if(email!=null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        }
        else
        {
            throw new BadCredentialsException("This email is already registered with provider"+emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto= new LoginResponseDto(authUtil.generateAccessToken(user),user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }

}
