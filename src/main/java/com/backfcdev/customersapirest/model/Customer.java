package com.backfcdev.customersapirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String direction;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @PrePersist
    private void assignCreationDate() {
        creationDate = LocalDateTime.now();
    }

    @PreUpdate
    private void assignUpdateDate() {
        updateDate = LocalDateTime.now();
    }
}
