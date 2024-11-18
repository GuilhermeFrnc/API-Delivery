package com.deliveries.apideliveries.entity;

import com.deliveries.apideliveries.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_client", nullable = false)
    private String nameClient;

    @Column(name = "name_store", nullable = false)
    private String nameStore;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "date_create", updatable = false)
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "date_delivery")
    private LocalDateTime deliveryDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;
}
