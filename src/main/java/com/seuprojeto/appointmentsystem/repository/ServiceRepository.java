package com.seuprojeto.appointmentsystem.repository;

import com.seuprojeto.appointmentsystem.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByActiveTrue();
    List<ServiceEntity> findByNameContainingIgnoreCase(String name);
}
