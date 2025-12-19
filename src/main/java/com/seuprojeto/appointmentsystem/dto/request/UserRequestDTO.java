package com.seuprojeto.appointmentsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    
    @NotBlank(message = "Nome não pode ser vazio")
    private String name;
    
    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email deve ser válido")
    private String email;
    
    @NotBlank(message = "Telefone não pode ser vazio")
    private String phone;
    
    @NotNull(message = "Role não pode ser nulo")
    private String role;
}
