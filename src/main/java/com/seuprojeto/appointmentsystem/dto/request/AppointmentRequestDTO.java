package com.seuprojeto.appointmentsystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {
    
    @NotNull(message = "ID do usuário não pode ser nulo")
    private Long userId;
    
    @NotNull(message = "ID do serviço não pode ser nulo")
    private Long serviceId;
    
    @NotNull(message = "Data e hora do agendamento não podem ser nulos")
    private LocalDateTime appointmentDateTime;
    
    private String notes;
}
