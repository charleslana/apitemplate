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

    public ResponseDTO login(LoginRequestDTO dto) {
        User user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new BusinessException("User not found"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BusinessException("Invalid credentials");
        }

        String token = tokenService.generateToken(user);
        return new ResponseDTO(user.getName(), token);
    }

    public ResponseDTO register(RegisterRequestDTO dto) {
        Optional<User> existingUser = repository.findByEmail(dto.email());

        if (existingUser.isPresent()) {
            throw new BusinessException("User already exists");
        }

        User newUser = new User();
        newUser.setName(dto.name());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        if (dto.role() != null) {
            newUser.setRole(dto.role());
        }
        repository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return new ResponseDTO(newUser.getName(), token);
    }
}
