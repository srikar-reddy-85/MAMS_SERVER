package com.mams.backend.repository;

import com.mams.backend.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
