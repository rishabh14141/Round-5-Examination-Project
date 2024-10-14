package com.example.examination.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;  // Generic type to hold any response data

    // Constructors
    public ApiResponse() {
    }

    public ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
