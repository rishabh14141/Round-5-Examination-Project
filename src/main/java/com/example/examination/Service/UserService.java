package com.example.examination.Service;

import com.example.examination.Manager.TestManager;
import com.example.examination.Manager.UserManager;
import com.example.examination.Model.Tests;
import com.example.examination.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserManager userManager;

    public Users createUser(Users user) {
        return userManager.createUser(user);
    }

    public Users updateUser(Users user) {
        return userManager.updateUser(user);
    }

    public Optional<Users> deleteUser(UUID userId) {
        return userManager.deleteUser((userId));
    }

    // Get tests dynamically based on different request parameters
    public List<Users> getUsersByParams(String name, String phoneNumber, UUID id) {
        return userManager.getUsersByParams(name, phoneNumber, id);
    }
}
