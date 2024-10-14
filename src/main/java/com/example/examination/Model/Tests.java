package com.example.examination.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "tests")
public class Tests {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id; // Integer as the primary key

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int level = 1; // Default value for level
}
