package com.distributiongraduates.repo;

import com.distributiongraduates.model.Score;
import com.distributiongraduates.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepo extends JpaRepository<Score, Long> {
    List<Score> findAllByOwner_Role(Role role);
}
