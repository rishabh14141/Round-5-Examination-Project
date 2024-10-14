package com.example.examination.Manager;

import com.example.examination.Dto.Response.UserParticipationResponseDto;
import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import com.example.examination.Model.Tests;
import com.example.examination.Model.UserParticipation;
import com.example.examination.Model.Users;
import com.example.examination.Repository.TestRepository;
import com.example.examination.Repository.UserParticipationRepository;
import com.example.examination.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserParticipationManager {

    @Autowired
    private UserParticipationRepository userParticipationRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;


    public UserParticipation createUserParticipation(UserParticipation userParticipation) {
        if(userParticipation.getStatus() != null && userParticipation.getStatus() != UserParticipationStatusEnum.REGISTERED) {
            throw new IllegalArgumentException("Status should be " + UserParticipationStatusEnum.REGISTERED);
        }

        List<UserParticipation> existingUserParticipation = userParticipationRepository.findByTestIdAndUserId(userParticipation.getTestId(), userParticipation.getUserId());

        if(!existingUserParticipation.isEmpty()) {
            throw new IllegalArgumentException("User already registered for test ID " + userParticipation.getTestId());
        }

        Optional<Tests> testData = testRepository.findById(userParticipation.getTestId());

        if(!testData.isPresent()) {
            throw new EntityNotFoundException("Test doesnot exist");
        } else {
            Tests test = testData.get();
            if(test.getLevel() != 1) {
                throw new EntityNotFoundException("Not eligible for next levels registration");
            }
        }

        Optional<Users> userData = userRepository.findById(userParticipation.getUserId());
        if(!userData.isPresent()) {
            throw new EntityNotFoundException("User doesnot exist");
        }

        return userParticipationRepository.save(userParticipation);
    }

    public UserParticipation updateUserParticipation(UserParticipation userParticipation) {
        Optional<UserParticipation> existingUserParticipationOptional = userParticipationRepository.findById(userParticipation.getId());

        if (existingUserParticipationOptional.isPresent()) {
            UserParticipation existingUserParticipation = existingUserParticipationOptional.get();

            existingUserParticipation.setUserId(userParticipation.getUserId());
            existingUserParticipation.setTestId(userParticipation.getTestId());
            existingUserParticipation.setStatus(userParticipation.getStatus());


            Optional<Tests> optionalTestData = testRepository.findById(existingUserParticipation.getTestId());

            if(!optionalTestData.isPresent()) {
                throw new EntityNotFoundException("Test doesnot exist");
            }
//            else {
//                Tests testData = optionalTestData.get();
//                if(testData.getLevel() != 1) {
//                    throw new EntityNotFoundException("Not eligible for next levels registration");
//                }
//            }

            Optional<Users> userData = userRepository.findById(userParticipation.getUserId());
            if(!userData.isPresent()) {
                throw new EntityNotFoundException("User doesnot exist");
            }

            if(existingUserParticipation.getStatus() == UserParticipationStatusEnum.CLEARED && optionalTestData.get().getLevel() == 1) {
                Tests test = optionalTestData.get();
                List<Tests> testList = testRepository.findByName(test.getName());

                if(testList.size() > 1) {

                    for( Integer i = 0 ; i < testList.size() ; i ++ ) {
                        Tests nextLevelTestData = testList.get(i);

                        if(nextLevelTestData.getLevel() != 1 ) {
                            UserParticipation nextLevelUserParticipation = userParticipation;
                            nextLevelUserParticipation.setId(UUID.randomUUID());
                            nextLevelUserParticipation.setTestId(nextLevelTestData.getId());
                            nextLevelUserParticipation.setStatus(UserParticipationStatusEnum.REGISTERED);
                            userParticipationRepository.save(nextLevelUserParticipation);
                            break;
                        }
                    }
                }
            }

            return userParticipationRepository.save(existingUserParticipation);
        } else {
            // Handle the case where the test does not exist
            throw new EntityNotFoundException("User participation with ID " + userParticipation.getId() + " not found.");
        }
    }

    @Transactional
    public Optional<UserParticipation> deleteUserParticipation(UUID userParticipationId) {
        Optional<UserParticipation> existingUserOptional = userParticipationRepository.findById(userParticipationId);

        if (existingUserOptional.isPresent()) {  // Check if the test exists
            return userParticipationRepository.deleteById(userParticipationId);    // Delete the test by its id
        } else {
            throw new IllegalArgumentException("Test with ID " + userParticipationId + " does not exist.");
        }
    }


    // Fetch tests based on parameters (id, name, level)
    public List<UserParticipationResponseDto> getUserParticipationByParams(UUID id, UUID testId, UUID userId, UserParticipationStatusEnum status) {
        List<UserParticipation> userParticipationList;

        if (id != null) {
            Optional<UserParticipation> result = userParticipationRepository.findById(id);
            userParticipationList =  result.map(List::of).orElse(List.of());
        } else if (testId != null && userId != null) {
            userParticipationList = userParticipationRepository.findByTestIdAndUserId(testId, userId);
        } else if (testId != null && status != null) {
            userParticipationList = userParticipationRepository.findByTestIdAndStatus(testId, status);
        } else if (status != null) {
            userParticipationList = userParticipationRepository.findByStatus(status);
        } else if (testId != null) {
            userParticipationList = userParticipationRepository.findByTestId(testId);
        } else if (userId != null) {
            userParticipationList = userParticipationRepository.findByUserId(userId);
        } else {
            userParticipationList = userParticipationRepository.findAll();
        }


        Set<UUID> testSet = userParticipationList.stream()
                .map(userParticipation -> userParticipation.getTestId())
                .collect(Collectors.toSet());

        Set<UUID> userSet = userParticipationList.stream()
                .map(userParticipation -> userParticipation.getUserId())
                .collect(Collectors.toSet());

        List<UUID> testIds = new ArrayList<>(testSet);
        List<UUID> userIds = new ArrayList<>(userSet);

        List<Users> usersList = userRepository.findAllByIdIn(userIds);
        List<Tests> testsList = testRepository.findAllByIdIn(testIds);


        Map<UUID, Users> userMap = new HashMap<>();
        for (Users user : usersList) {
            userMap.put(user.getId(), user);
        }

        Map<UUID, Tests> testMap = new HashMap<>();
        for (Tests test : testsList) {
            testMap.put(test.getId(), test);
        }

        List<UserParticipationResponseDto> userParticipationResponseDtoList = new ArrayList<>();

        for( Integer i = 0; i < userParticipationList.size() ; i++ ) {
            UserParticipation userParticipation = userParticipationList.get(i);

            Tests testData = testMap.get(userParticipation.getTestId());
            Users userData = userMap.get(userParticipation.getUserId());

            UserParticipationResponseDto.UserDto userDto = new UserParticipationResponseDto.UserDto(
                    userData.getId(),
                    userData.getName(),
                    userData.getPhoneNumber()
            );

            UserParticipationResponseDto.TestDto testDto = new UserParticipationResponseDto.TestDto(
                    testData.getId(),
                    testData.getName(),
                    testData.getLevel()
            );

            // Return the structured response using Lombok-generated constructors
            userParticipationResponseDtoList.add( new UserParticipationResponseDto(
                    userParticipation.getId(),
                    userDto,
                    testDto,
                    userParticipation.getStatus()  // Assuming status is a field in the UserParticipation entity
            ));
        }

        return userParticipationResponseDtoList;
    }
}
