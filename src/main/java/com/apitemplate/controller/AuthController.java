package com.apitemplate.controller;

import com.apitemplate.dto.LoginRequestDTO;
import com.apitemplate.dto.RegisterRequestDTO;
import com.apitemplate.dto.ResponseDTO;
import com.apitemplate.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login do usuário", description = "Autentica um usuário e retorna um token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou erro de autenticação")
    })
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        log.info("REST request to login user: {}", dto);
        ResponseDTO response = authService.login(dto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Registro do usuário", description = "Registra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao registrar o usuário")
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterRequestDTO dto) {
        log.info("REST request to create user: {}", dto);
        ResponseDTO response = authService.register(dto);
        return ResponseEntity.ok(response);
    }
}
