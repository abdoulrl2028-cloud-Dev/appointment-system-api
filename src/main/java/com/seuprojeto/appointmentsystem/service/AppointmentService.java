package com.seuprojeto.appointmentsystem.service;

import com.seuprojeto.appointmentsystem.dto.request.AppointmentRequestDTO;
import com.seuprojeto.appointmentsystem.dto.response.AppointmentResponseDTO;
import com.seuprojeto.appointmentsystem.entity.Appointment;
import com.seuprojeto.appointmentsystem.entity.ServiceEntity;
import com.seuprojeto.appointmentsystem.entity.User;
import com.seuprojeto.appointmentsystem.exception.BusinessException;
import com.seuprojeto.appointmentsystem.exception.ResourceNotFoundException;
import com.seuprojeto.appointmentsystem.repository.AppointmentRepository;
import com.seuprojeto.appointmentsystem.repository.ServiceRepository;
import com.seuprojeto.appointmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    
    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + requestDTO.getUserId()));
        
        ServiceEntity service = serviceRepository.findById(requestDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + requestDTO.getServiceId()));
        
        if (requestDTO.getAppointmentDateTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Não é possível agendar para data e hora no passado");
        }
        
        Appointment appointment = Appointment.builder()
                .user(user)
                .service(service)
                .appointmentDateTime(requestDTO.getAppointmentDateTime())
                .notes(requestDTO.getNotes())
                .status("SCHEDULED")
                .build();
        
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToResponseDTO(savedAppointment);
    }
    
    @Transactional(readOnly = true)
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
        return convertToResponseDTO(appointment);
    }
    
    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getAppointmentsByUserId(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + userId));
        
        return appointmentRepository.findByUserId(userId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
        
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + requestDTO.getUserId()));
        
        ServiceEntity service = serviceRepository.findById(requestDTO.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + requestDTO.getServiceId()));
        
        if (requestDTO.getAppointmentDateTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Não é possível agendar para data e hora no passado");
        }
        
        appointment.setUser(user);
        appointment.setService(service);
        appointment.setAppointmentDateTime(requestDTO.getAppointmentDateTime());
        appointment.setNotes(requestDTO.getNotes());
        
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return convertToResponseDTO(updatedAppointment);
    }
    
    @Transactional
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
        appointmentRepository.delete(appointment);
    }
    
    @Transactional
    public AppointmentResponseDTO cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
        
        if ("CANCELLED".equals(appointment.getStatus())) {
            throw new BusinessException("Agendamento já foi cancelado");
        }
        
        appointment.setStatus("CANCELLED");
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return convertToResponseDTO(updatedAppointment);
    }
    
    @Transactional
    public AppointmentResponseDTO confirmAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
        
        appointment.setStatus("CONFIRMED");
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return convertToResponseDTO(updatedAppointment);
    }
    
    private AppointmentResponseDTO convertToResponseDTO(Appointment appointment) {
        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .userId(appointment.getUser().getId())
                .userName(appointment.getUser().getName())
                .serviceId(appointment.getService().getId())
                .serviceName(appointment.getService().getName())
                .appointmentDateTime(appointment.getAppointmentDateTime())
                .status(appointment.getStatus())
                .notes(appointment.getNotes())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .build();
    }
}
