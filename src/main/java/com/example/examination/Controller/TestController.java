package com.example.examination.Controller;

import com.example.examination.Dto.Request.CreateTestDto;
import com.example.examination.Model.Tests;
import com.example.examination.Repository.TestRepository;
import com.example.examination.Response.ApiResponse;
import com.example.examination.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Tests>>> getTestsByParams(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer level
    ) {
        List<Tests> tests = testService.getTestsByParams(name, level, id);

        if (tests.isEmpty()) {
            ApiResponse<List<Tests>> data = new ApiResponse<>(HttpStatus.BAD_REQUEST, "No tests found with given parameters", null);
            return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
        }

        ApiResponse<List<Tests>> data = new ApiResponse<>(HttpStatus.OK, "Tests fetched successfully", tests);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


    @PostMapping("")
    public Tests createTest(@RequestBody Tests test) {
        return testService.createTest(test);
    }

    @PutMapping("")
    public Tests updateTest(@RequestBody Tests test) {
        return testService.updateTest(test);
    }

    @DeleteMapping("/{testId}")
    public void deleteTest(@PathVariable UUID testId) {
        Optional<Tests> data= testService.deleteTest(testId);
    }
}
