package com.seuprojeto.appointmentsystem.controller;

import com.seuprojeto.appointmentsystem.dto.request.ServiceRequestDTO;
import com.seuprojeto.appointmentsystem.dto.response.ServiceResponseDTO;
import com.seuprojeto.appointmentsystem.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
@Tag(name = "Serviços", description = "Endpoints para gerenciar serviços")
public class ServiceController {
    
    private final ServiceService serviceService;
    
    @PostMapping
    @Operation(summary = "Criar novo serviço", description = "Cria um novo serviço no sistema")
    public ResponseEntity<ServiceResponseDTO> createService(@Valid @RequestBody ServiceRequestDTO requestDTO) {
        ServiceResponseDTO response = serviceService.createService(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obter serviço por ID", description = "Retorna os dados de um serviço específico")
    public ResponseEntity<ServiceResponseDTO> getServiceById(@PathVariable Long id) {
        ServiceResponseDTO response = serviceService.getServiceById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Listar todos os serviços", description = "Retorna uma lista de todos os serviços")
    public ResponseEntity<List<ServiceResponseDTO>> getAllServices() {
        List<ServiceResponseDTO> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }
    
    @GetMapping("/active")
    @Operation(summary = "Listar serviços ativos", description = "Retorna uma lista de serviços ativos")
    public ResponseEntity<List<ServiceResponseDTO>> getActiveServices() {
        List<ServiceResponseDTO> services = serviceService.getActiveServices();
        return ResponseEntity.ok(services);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Buscar serviços por nome", description = "Busca serviços que correspondem ao nome informado")
    public ResponseEntity<List<ServiceResponseDTO>> searchServicesByName(
            @RequestParam String name) {
        List<ServiceResponseDTO> services = serviceService.searchServicesByName(name);
        return ResponseEntity.ok(services);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar serviço", description = "Atualiza os dados de um serviço existente")
    public ResponseEntity<ServiceResponseDTO> updateService(
            @PathVariable Long id,
            @Valid @RequestBody ServiceRequestDTO requestDTO) {
        ServiceResponseDTO response = serviceService.updateService(id, requestDTO);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar serviço", description = "Remove um serviço do sistema")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
