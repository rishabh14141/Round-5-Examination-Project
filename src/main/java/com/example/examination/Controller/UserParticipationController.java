package com.example.examination.Controller;

import com.example.examination.Dto.Response.UserParticipationResponseDto;
import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import com.example.examination.Model.UserParticipation;
import com.example.examination.Response.ApiResponse;
import com.example.examination.Service.UserParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user-participation")
public class UserParticipationController {

    @Autowired
    private UserParticipationService userParticipationServiceService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<UserParticipationResponseDto>>> getUserParticipationByParams(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) UUID testId,
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) UserParticipationStatusEnum status
            ) {
        List<UserParticipationResponseDto> users = userParticipationServiceService.getUserParticipationByParams(id, testId, userId, status);

        if (users.isEmpty()) {
            ApiResponse<List<UserParticipationResponseDto>> data = new ApiResponse<>(HttpStatus.BAD_REQUEST, "No user found with given parameters", null);
            return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
        }

        ApiResponse<List<UserParticipationResponseDto>> data = new ApiResponse<>(HttpStatus.OK, "Users fetched successfully", users);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @PostMapping("")
    public UserParticipation createUserParticipation(@RequestBody UserParticipation userParticipation) {
        return userParticipationServiceService.createUserParticipation(userParticipation);
    }

    @PutMapping("")
    public UserParticipation updateUserParticipation(@RequestBody UserParticipation userParticipation) {
        return userParticipationServiceService.updateUserParticipation(userParticipation);
    }

    @DeleteMapping("/{userParticipationId}")
    public void deleteUserParticipation(@PathVariable UUID userParticipationId) {
        Optional<UserParticipation> data= userParticipationServiceService.deleteUserParticipation(userParticipationId);
    }
}
