package com.example.examination.Service;

import com.example.examination.Manager.TestManager;
import com.example.examination.Model.Tests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TestService {
    @Autowired
    private TestManager testManager;

    public Tests createTest(Tests test) {
        return testManager.createTest(test);
    }

    public Tests updateTest(Tests test) {
        return testManager.updateTest(test);
    }

    public Optional<Tests> deleteTest(UUID testId) {
        return testManager.deleteTest((testId));
    }

    // Get tests dynamically based on different request parameters
    public List<Tests> getTestsByParams(String name, Integer level, UUID id) {
        return testManager.getTestsByParams(name, level, id);
    }
}
