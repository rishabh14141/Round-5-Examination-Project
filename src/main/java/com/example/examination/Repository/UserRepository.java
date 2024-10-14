package com.example.examination.Repository;

import com.example.examination.Model.Tests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.examination.Model.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(UUID userId);

    Optional<Users> deleteById(UUID userId);

    List<Users> findByName(String name);

    List<Users> findByPhoneNumber(String phoneNumber);

    List<Users> findByNameAndPhoneNumber(String name, String phoneNumber);

    List<Users> findAllByIdIn(List<UUID> userIds);
}