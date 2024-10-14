package com.example.examination.Manager;

import com.example.examination.Model.Tests;
import com.example.examination.Repository.TestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TestManager {

        @Autowired
        private TestRepository testRepository;

        public Tests createTest(Tests test)  {
            return testRepository.save(test);
        }

     public Tests updateTest(Tests test) {
        Optional<Tests> existingTestOptional = testRepository.findById(test.getId());

        if (existingTestOptional.isPresent()) {
            Tests existingTest = existingTestOptional.get();

            existingTest.setName(test.getName());
            existingTest.setLevel(test.getLevel());

            return testRepository.save(existingTest);
        } else {
            throw new EntityNotFoundException("Test with ID " + test.getId() + " not found.");
        }
    }

    @Transactional
    public Optional<Tests> deleteTest(UUID testId) {
        Optional<Tests> existingTestOptional = testRepository.findById(testId);

        if (existingTestOptional.isPresent()) {  // Check if the test exists
            return testRepository.deleteById(testId);    // Delete the test by its id
        } else {
            throw new IllegalArgumentException("Test with ID " + testId + " does not exist.");
        }
    }


    public List<Tests> getTestsByParams(String name, Integer level, UUID id) {
        if (id != null) {
            Optional<Tests> result = testRepository.findById(id);
            return result.map(List::of).orElse(List.of());
        } else if (name != null && level != null) {
            return testRepository.findByNameAndLevel(name, level);
        } else if (name != null) {
            return testRepository.findByName(name);
        } else if (level != null) {
            return testRepository.findByLevel(level);
        } else {
            return testRepository.findAll();
        }
    }
}
