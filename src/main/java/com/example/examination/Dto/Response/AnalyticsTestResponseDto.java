package com.example.examination.Dto.Response;

import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Data
public class AnalyticsTestResponseDto {
    private Map<String, Long> statusCounts = new HashMap<>();

    public AnalyticsTestResponseDto(Map<String, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }
}
