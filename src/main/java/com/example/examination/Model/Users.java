package com.example.examination.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;  // UUID as the primary key

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 10)
    private String phoneNumber; // Links to Tests entity
}