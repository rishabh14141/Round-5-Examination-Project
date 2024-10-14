package com.example.examination.Dto.Request;

import lombok.Data;

@Data
public class CreateUserRegistrationDto {
    private String name;
    private String phoneNumber;
    private String testId;
}