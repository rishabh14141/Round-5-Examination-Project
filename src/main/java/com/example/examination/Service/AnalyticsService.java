package com.example.examination.Service;

import com.example.examination.Dto.Response.AnalyticsTestResponseDto;
import com.example.examination.Manager.AnalyticsManager;
import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyticsService {
    @Autowired
    private AnalyticsManager analyticsManager;
    public AnalyticsTestResponseDto getTestAnalytics(UUID testId) {
        List<Object[]> analyticsData = analyticsManager.getTestAnalytics(testId);

        Map<String, Long> statusCounts = new HashMap<>();

        for (UserParticipationStatusEnum status : UserParticipationStatusEnum.values()) {
            statusCounts.put(status.name(), 0L);
        }

        for (Object[] data : analyticsData) {
            Long count = (Long) data[0];
            UserParticipationStatusEnum status = (UserParticipationStatusEnum) data[1];
            statusCounts.put(status.name(), count);
        }

        return new AnalyticsTestResponseDto(statusCounts);

    }
}
