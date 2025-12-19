package com.seuprojeto.appointmentsystem.service;

import com.seuprojeto.appointmentsystem.dto.request.ServiceRequestDTO;
import com.seuprojeto.appointmentsystem.dto.response.ServiceResponseDTO;
import com.seuprojeto.appointmentsystem.entity.ServiceEntity;
import com.seuprojeto.appointmentsystem.exception.ResourceNotFoundException;
import com.seuprojeto.appointmentsystem.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceService {
    
    private final ServiceRepository serviceRepository;
    
    @Transactional
    public ServiceResponseDTO createService(ServiceRequestDTO requestDTO) {
        ServiceEntity service = ServiceEntity.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .price(requestDTO.getPrice())
                .durationMinutes(requestDTO.getDurationMinutes())
                .active(requestDTO.getActive() != null ? requestDTO.getActive() : true)
                .build();
        
        ServiceEntity savedService = serviceRepository.save(service);
        return convertToResponseDTO(savedService);
    }
    
    @Transactional(readOnly = true)
    public ServiceResponseDTO getServiceById(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + id));
        return convertToResponseDTO(service);
    }
    
    @Transactional(readOnly = true)
    public List<ServiceResponseDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<ServiceResponseDTO> getActiveServices() {
        return serviceRepository.findByActiveTrue().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<ServiceResponseDTO> searchServicesByName(String name) {
        return serviceRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public ServiceResponseDTO updateService(Long id, ServiceRequestDTO requestDTO) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + id));
        
        service.setName(requestDTO.getName());
        service.setDescription(requestDTO.getDescription());
        service.setPrice(requestDTO.getPrice());
        service.setDurationMinutes(requestDTO.getDurationMinutes());
        service.setActive(requestDTO.getActive() != null ? requestDTO.getActive() : true);
        
        ServiceEntity updatedService = serviceRepository.save(service);
        return convertToResponseDTO(updatedService);
    }
    
    @Transactional
    public void deleteService(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + id));
        serviceRepository.delete(service);
    }
    
    private ServiceResponseDTO convertToResponseDTO(ServiceEntity service) {
        return ServiceResponseDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .active(service.getActive())
                .createdAt(service.getCreatedAt())
                .updatedAt(service.getUpdatedAt())
                .build();
    }
}
