package com.apitemplate.controller;

import com.apitemplate.dto.UserRequestDTO;
import com.apitemplate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @Operation(summary = "Obter dados do usuário", description = "Retorna as informações do usuário autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping
    public ResponseEntity<UserRequestDTO> getUser() {
        log.info("REST request to user details");
        UserRequestDTO userDTO = userService.getAuthenticatedUser();
        return ResponseEntity.ok(userDTO);
    }
}
