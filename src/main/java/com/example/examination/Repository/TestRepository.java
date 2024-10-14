package com.example.examination.Repository;

import com.example.examination.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.examination.Model.Tests;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Tests, Long> {
    Optional<Tests> findById(UUID testId);

    Optional<Tests> deleteById(UUID testId);

    List<Tests> findByName(String name);

    List<Tests> findByLevel(int level);

    List<Tests> findByNameAndLevel(String name, int level);

    List<Tests> findAllByIdIn(List<UUID> testIds);
}
