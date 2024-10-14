package com.example.examination.Manager;

import com.example.examination.Model.Users;
import com.example.examination.Repository.UserParticipationRepository;
import com.example.examination.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AnalyticsManager {
    @Autowired
    private UserParticipationRepository userParticipationRepository;

    public List<Object[]>  getTestAnalytics(UUID testId) {
        return userParticipationRepository.countDistinctUsersByTestId(testId);
    }
}


//
//package com.example.examination.Manager;
//
//import com.example.examination.Model.Tests;
//import com.example.examination.Model.Users;
//import com.example.examination.Repository.TestRepository;
//import com.example.examination.Repository.UserRepository;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//
//public class UserManager {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public Users createUser(Users user)  {
//        return userRepository.save(user);
//    }
//
//    public Users updateUser(Users user) {
//        Optional<Users> existingUserOptional = userRepository.findById(user.getId());
//
//        if (existingUserOptional.isPresent()) {
//            Users existingUser = existingUserOptional.get();
//
//            existingUser.setName(user.getName());
//            existingUser.setPhoneNumber(user.getPhoneNumber());
//
//            return userRepository.save(existingUser);
//        } else {
//            // Handle the case where the test does not exist
//            throw new EntityNotFoundException("User with ID " + user.getId() + " not found.");
//        }
//    }
//
//    @Transactional
//    public Optional<Users> deleteUser(UUID userId) {
//        Optional<Users> existingUserOptional = userRepository.findById(userId);
//
//        if (existingUserOptional.isPresent()) {  // Check if the test exists
//            return userRepository.deleteById(userId);    // Delete the test by its id
//        } else {
//            throw new IllegalArgumentException("Test with ID " + userId + " does not exist.");
//        }
//    }
//
//
//    // Fetch tests based on parameters (id, name, level)
//    public List<Users> getUsersByParams(String name, String phoneNumber, UUID id) {
//        if (id != null) {
//            // If id is provided, search by id
//            Optional<Users> result = userRepository.findById(id);
//            return result.map(List::of).orElse(List.of());
//        } else if (name != null && phoneNumber != null) {
//            // If both name and level are provided, search by both
//            return userRepository.findByNameAndPhoneNumber(name, phoneNumber);
//        } else if (name != null) {
//            // If only name is provided, search by name
//            return userRepository.findByName(name);
//        } else if (phoneNumber != null) {
//            // If only level is provided, search by level
//            return userRepository.findByPhoneNumber(phoneNumber);
//        } else {
//            // If no params are provided, return all tests
//            return userRepository.findAll();
//        }
//    }
//}
