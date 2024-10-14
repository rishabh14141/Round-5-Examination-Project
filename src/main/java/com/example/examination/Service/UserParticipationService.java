package com.example.examination.Service;

import com.example.examination.Dto.Response.UserParticipationResponseDto;
import com.example.examination.Manager.UserParticipationManager;
import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import com.example.examination.Model.UserParticipation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserParticipationService {
    @Autowired
    private UserParticipationManager userParticipationManager;

    public UserParticipation createUserParticipation(UserParticipation userParticipation) {
        return userParticipationManager.createUserParticipation(userParticipation);
    }

    public UserParticipation updateUserParticipation(UserParticipation userParticipation) {
        return userParticipationManager.updateUserParticipation(userParticipation);
    }

    public Optional<UserParticipation> deleteUserParticipation(UUID userParticipationId) {
        return userParticipationManager.deleteUserParticipation((userParticipationId));
    }

    public List<UserParticipationResponseDto> getUserParticipationByParams(UUID id, UUID testId, UUID userId, UserParticipationStatusEnum status) {
        return userParticipationManager.getUserParticipationByParams(id, testId, userId, status);
    }
}
