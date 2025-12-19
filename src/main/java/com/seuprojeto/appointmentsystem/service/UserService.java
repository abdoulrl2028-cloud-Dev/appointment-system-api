package com.seuprojeto.appointmentsystem.service;

import com.seuprojeto.appointmentsystem.dto.request.UserRequestDTO;
import com.seuprojeto.appointmentsystem.dto.response.UserResponseDTO;
import com.seuprojeto.appointmentsystem.entity.User;
import com.seuprojeto.appointmentsystem.exception.BusinessException;
import com.seuprojeto.appointmentsystem.exception.ResourceNotFoundException;
import com.seuprojeto.appointmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new BusinessException("Email já cadastrado no sistema");
        }
        
        User user = User.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .role(requestDTO.getRole())
                .build();
        
        User savedUser = userRepository.save(user);
        return convertToResponseDTO(savedUser);
    }
    
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        return convertToResponseDTO(user);
    }
    
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com email: " + email));
        return convertToResponseDTO(user);
    }
    
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        
        if (!user.getEmail().equals(requestDTO.getEmail()) && 
            userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new BusinessException("Email já cadastrado no sistema");
        }
        
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPhone(requestDTO.getPhone());
        user.setRole(requestDTO.getRole());
        
        User updatedUser = userRepository.save(user);
        return convertToResponseDTO(updatedUser);
    }
    
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        userRepository.delete(user);
    }
    
    private UserResponseDTO convertToResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
