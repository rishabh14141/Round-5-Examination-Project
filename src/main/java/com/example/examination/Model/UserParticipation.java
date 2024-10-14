package com.example.examination.Model;

import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Table(name = "userParticipation")
public class UserParticipation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;  // UUID as the primary key

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID testId;  // Links to Tests entity

    @Column(nullable = false)
    private UserParticipationStatusEnum status = UserParticipationStatusEnum.REGISTERED;  // Links to Tests entity
}