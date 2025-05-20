package com.mams.backend.repository;

import com.mams.backend.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
}
