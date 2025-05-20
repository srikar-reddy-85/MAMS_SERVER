package com.mams.backend.controller;

import com.mams.backend.dto.PersonnelDTO;
import com.mams.backend.entity.Personnel;
import com.mams.backend.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personnel")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelService service;

    @PostMapping
    public ResponseEntity<Personnel> addPersonnel(@RequestBody PersonnelDTO dto) {
        return ResponseEntity.ok(service.addPersonnel(dto));
    }

    @GetMapping
    public ResponseEntity<List<Personnel>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> update(@PathVariable Long id, @RequestBody PersonnelDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
