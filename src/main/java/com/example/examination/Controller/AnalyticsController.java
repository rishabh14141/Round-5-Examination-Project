package com.example.examination.Controller;

import com.example.examination.Dto.Request.CreateTestDto;
import com.example.examination.Dto.Response.AnalyticsTestResponseDto;
import com.example.examination.Dto.Response.UserParticipationResponseDto;
import com.example.examination.Model.Tests;
import com.example.examination.Repository.TestRepository;
import com.example.examination.Response.ApiResponse;
import com.example.examination.Service.AnalyticsService;
import com.example.examination.Service.TestService;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<AnalyticsTestResponseDto>> getTestAnalytics (
            @RequestParam(required = true) UUID testId
    ) {
        AnalyticsTestResponseDto analyticsData = analyticsService.getTestAnalytics(testId);
        ApiResponse<AnalyticsTestResponseDto> data = new ApiResponse<>(HttpStatus.OK, "Test Analytics fetched successfully", analyticsData);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
