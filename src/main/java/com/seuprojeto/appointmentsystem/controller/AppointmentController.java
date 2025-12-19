package com.seuprojeto.appointmentsystem.controller;

import com.seuprojeto.appointmentsystem.dto.request.AppointmentRequestDTO;
import com.seuprojeto.appointmentsystem.dto.response.AppointmentResponseDTO;
import com.seuprojeto.appointmentsystem.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Endpoints para gerenciar agendamentos")
public class AppointmentController {
    
    private final AppointmentService appointmentService;
    
    @PostMapping
    @Operation(summary = "Criar novo agendamento", description = "Cria um novo agendamento no sistema")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @Valid @RequestBody AppointmentRequestDTO requestDTO) {
        AppointmentResponseDTO response = appointmentService.createAppointment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obter agendamento por ID", description = "Retorna os dados de um agendamento específico")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDTO response = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os agendamentos", description = "Retorna uma lista de todos os agendamentos")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        List<AppointmentResponseDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Obter agendamentos de um usuário", description = "Retorna todos os agendamentos de um usuário específico")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByUserId(
            @PathVariable Long userId) {
        List<AppointmentResponseDTO> appointments = appointmentService.getAppointmentsByUserId(userId);
        return ResponseEntity.ok(appointments);
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Obter agendamentos por status", description = "Retorna agendamentos com status específico")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByStatus(
            @PathVariable String status) {
        List<AppointmentResponseDTO> appointments = appointmentService.getAppointmentsByStatus(status);
        return ResponseEntity.ok(appointments);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agendamento", description = "Atualiza os dados de um agendamento existente")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequestDTO requestDTO) {
        AppointmentResponseDTO response = appointmentService.updateAppointment(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancelar agendamento", description = "Cancela um agendamento existente")
    public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable Long id) {
        AppointmentResponseDTO response = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/confirm")
    @Operation(summary = "Confirmar agendamento", description = "Confirma um agendamento existente")
    public ResponseEntity<AppointmentResponseDTO> confirmAppointment(@PathVariable Long id) {
        AppointmentResponseDTO response = appointmentService.confirmAppointment(id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar agendamento", description = "Remove um agendamento do sistema")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
