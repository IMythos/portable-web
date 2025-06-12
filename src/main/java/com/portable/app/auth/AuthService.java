package com.portable.app.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.portable.app.dto.LoginRequestDto;
import com.portable.app.dto.LoginResponseDto;
import com.portable.app.entity.User;
import com.portable.app.repository.UserRepository;
import com.portable.app.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDto login (LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());


        return new LoginResponseDto(token, user.getRole().toString());
    }
}
