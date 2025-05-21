package com.mams.backend.controller;

import com.mams.backend.dto.AssignmentDTO;
import com.mams.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public AssignmentDTO create(@RequestBody AssignmentDTO dto) {
        return assignmentService.createAssignment(dto);
    }

    @GetMapping
    public List<AssignmentDTO> getAll() {
        return assignmentService.getAllAssignments();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
    }
}
