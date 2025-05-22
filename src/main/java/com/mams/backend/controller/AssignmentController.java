//package com.mams.backend.controller;
//
//import com.mams.backend.dto.AssignmentDTO;
//import com.mams.backend.service.AssignmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/assignments")
//@RequiredArgsConstructor
//public class AssignmentController {
//
//    private final AssignmentService assignmentService;
//
//    @PostMapping
//    public AssignmentDTO create(@RequestBody AssignmentDTO dto) {
//        return assignmentService.createAssignment(dto);
//    }
//
//    @GetMapping
//    public List<AssignmentDTO> getAll() {
//        return assignmentService.getAllAssignments();
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        assignmentService.deleteAssignment(id);
//    }
//}

// Enhanced AssignmentController.java with RBAC
package com.mams.backend.controller;

import com.mams.backend.dto.AssignmentDTO;
import com.mams.backend.security.BaseCommanderOnly;
import com.mams.backend.security.CheckBaseAccess;
import com.mams.backend.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    @BaseCommanderOnly
    public ResponseEntity<AssignmentDTO> create(@RequestBody AssignmentDTO dto) {
        return ResponseEntity.ok(assignmentService.createAssignment(dto));
    }

    @GetMapping
    @BaseCommanderOnly
    public ResponseEntity<List<AssignmentDTO>> getAll() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

//    @GetMapping("/location/{locationId}")
//    @CheckBaseAccess
//    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByLocation(@PathVariable Long locationId) {
//        return ResponseEntity.ok(assignmentService.getAssignmentsByLocation(locationId));
//    }

//    @GetMapping("/personnel/{personnelId}")
//    @BaseCommanderOnly
//    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByPersonnel(@PathVariable Long personnelId) {
//        return ResponseEntity.ok(assignmentService.getAssignmentsByPersonnel(personnelId));
//    }
//
//    @GetMapping("/{id}")
//    @BaseCommanderOnly
//    public ResponseEntity<AssignmentDTO> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
//    }
//
//    @PutMapping("/{id}")
//    @BaseCommanderOnly
//    public ResponseEntity<AssignmentDTO> update(@PathVariable Long id, @RequestBody AssignmentDTO dto) {
//        return ResponseEntity.ok(assignmentService.updateAssignment(id, dto));
//    }

    @DeleteMapping("/{id}")
    @BaseCommanderOnly
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
