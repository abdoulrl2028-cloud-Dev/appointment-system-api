package com.seuprojeto.appointmentsystem.controller;

import com.seuprojeto.appointmentsystem.dto.request.UserRequestDTO;
import com.seuprojeto.appointmentsystem.dto.response.UserResponseDTO;
import com.seuprojeto.appointmentsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Endpoints para gerenciar usuários")
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no sistema")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO response = userService.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obter usuário por ID", description = "Retorna os dados de um usuário específico")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/email/{email}")
    @Operation(summary = "Obter usuário por email", description = "Retorna os dados de um usuário pelo email")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO response = userService.updateUser(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
