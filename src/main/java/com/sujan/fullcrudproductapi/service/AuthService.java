package com.sujan.fullcrudproductapi.service;

import com.sujan.fullcrudproductapi.dto.AuthResponse;
import com.sujan.fullcrudproductapi.dto.LoginRequest;
import com.sujan.fullcrudproductapi.dto.RegisterRequest;
import com.sujan.fullcrudproductapi.exception.DuplicateResourceException;
import com.sujan.fullcrudproductapi.model.Role;
import com.sujan.fullcrudproductapi.model.User;
import com.sujan.fullcrudproductapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;


    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicateResourceException("Username already taken");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password())); // BCrypt
        user.setRole(Role.ROLE_USER); //default role

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        // AuthenticationManager verifies credentials
        // Throws BadCredentialsException if wrong — Spring handles the 401
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}