package com.seuprojeto.appointmentsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequestDTO {
    
    @NotBlank(message = "Nome do serviço não pode ser vazio")
    private String name;
    
    private String description;
    
    @NotNull(message = "Preço não pode ser nulo")
    @Positive(message = "Preço deve ser positivo")
    private BigDecimal price;
    
    @NotNull(message = "Duração não pode ser nula")
    @Positive(message = "Duração deve ser positiva")
    private Integer durationMinutes;
    
    private Boolean active = true;
}
