package com.example.examination.Repository;

import com.example.examination.Model.Enums.UserParticipationStatusEnum;
import com.example.examination.Model.UserParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserParticipationRepository extends JpaRepository<UserParticipation, Long> {

    Optional<UserParticipation> findById(UUID userId);

    Optional<UserParticipation> deleteById(UUID userId);

    List<UserParticipation> findByTestIdAndUserId(UUID testId, UUID userId);

    List<UserParticipation> findByTestIdAndStatus(UUID testId, UserParticipationStatusEnum status);

    List<UserParticipation> findByStatus(UserParticipationStatusEnum status);

    List<UserParticipation> findByTestId(UUID testId);

    List<UserParticipation> findByUserId(UUID userId);

    @Query("SELECT COUNT(DISTINCT up.userId), up.status FROM UserParticipation up WHERE up.testId = :testId GROUP BY up.status")
    List<Object[]> countDistinctUsersByTestId(UUID testId);
}
