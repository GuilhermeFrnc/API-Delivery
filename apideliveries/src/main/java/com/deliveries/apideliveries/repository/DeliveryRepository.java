package com.deliveries.apideliveries.repository;

import com.deliveries.apideliveries.entity.Delivery;
import com.deliveries.apideliveries.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query("SELECT d FROM Delivery d WHERE d.createDateTime >= :startDate AND d.createDateTime <= :endDate")
    List<Delivery> findAllByCreateDateTimeBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Delivery> findAllByStatus(DeliveryStatus status);
}
