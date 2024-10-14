package com.example.examination.Dto.Response;

import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserParticipationResponseDto {

    private UUID id;
    private UserDto user;
    private TestDto test;
    private UserParticipationStatusEnum status;

    // Lombok will handle the generation of constructors, getters, and setters
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {
        private UUID id;
        private String name;
        private String phoneNumber;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestDto {
        private UUID id;
        private String name;
        private int level;
    }
}

