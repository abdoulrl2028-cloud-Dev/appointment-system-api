package com.seuprojeto.appointmentsystem.repository;

import com.seuprojeto.appointmentsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByStatus(String status);
    
    @Query("SELECT a FROM Appointment a WHERE a.service.id = :serviceId AND a.appointmentDateTime BETWEEN :startDate AND :endDate")
    List<Appointment> findByServiceAndDateRange(
        @Param("serviceId") Long serviceId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    List<Appointment> findByUserIdAndStatus(Long userId, String status);
}
