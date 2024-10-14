package com.example.examination.Controller;

import com.example.examination.Dto.Request.CreateTestDto;
import com.example.examination.Model.Tests;
import com.example.examination.Model.Users;
import com.example.examination.Repository.TestRepository;
import com.example.examination.Response.ApiResponse;
import com.example.examination.Service.TestService;
import com.example.examination.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Users>>> getUsersByParams(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNumber
    ) {
        List<Users> users = userService.getUsersByParams(name, phoneNumber, id);

        if (users.isEmpty()) {
            ApiResponse<List<Users>> data = new ApiResponse<>(HttpStatus.BAD_REQUEST, "No user found with given parameters", null);
            return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
        }

        ApiResponse<List<Users>> data = new ApiResponse<>(HttpStatus.OK, "Users fetched successfully", users);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @PostMapping("")
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PutMapping("")
    public Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        Optional<Users> data= userService.deleteUser(userId);
    }
}
