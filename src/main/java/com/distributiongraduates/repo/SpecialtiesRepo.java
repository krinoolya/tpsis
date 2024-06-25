package com.distributiongraduates.repo;

import com.distributiongraduates.model.Specialties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtiesRepo extends JpaRepository<Specialties, Long> {

}
