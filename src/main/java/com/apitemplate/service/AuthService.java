package com.apitemplate.service;

import com.apitemplate.domain.user.User;
import com.apitemplate.dto.LoginRequestDTO;
import com.apitemplate.dto.RegisterRequestDTO;
import com.apitemplate.dto.ResponseDTO;
import com.apitemplate.exception.BusinessException;
import com.apitemplate.infra.security.TokenService;
import com.apitemplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseDTO login(LoginRequestDTO body) {
        User user = repository.findByEmail(body.email())
                .orElseThrow(() -> new BusinessException("User not found"));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new BusinessException("Invalid credentials");
        }

        String token = tokenService.generateToken(user);
        return new ResponseDTO(user.getName(), token);
    }

    public ResponseDTO register(RegisterRequestDTO body) {
        Optional<User> existingUser = repository.findByEmail(body.email());

        if (existingUser.isPresent()) {
            throw new BusinessException("User already exists");
        }

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(passwordEncoder.encode(body.password()));
        repository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return new ResponseDTO(newUser.getName(), token);
    }
}
