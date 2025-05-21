package com.mams.backend.service;

import com.mams.backend.dto.AssignmentDTO;
import com.mams.backend.entity.*;
import com.mams.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final PersonnelRepository personnelRepository;
    private final AssetRepository assetRepository;
    private final LocationRepository locationRepository;

    public AssignmentDTO createAssignment(AssignmentDTO dto) {
        Assignment assignment = Assignment.builder()
                .assignmentDate(dto.getAssignmentDate())
                .status(dto.getStatus())
                .personnel(personnelRepository.findById(dto.getPersonnelId()).orElse(null))
                .asset(assetRepository.findById(dto.getAssetId()).orElse(null))
                .location(locationRepository.findById(dto.getLocationId()).orElseThrow())
                .build();

        Assignment saved = assignmentRepository.save(assignment);
        dto.setId(saved.getId());
        return dto;
    }

    public List<AssignmentDTO> getAllAssignments() {
        return assignmentRepository.findAll().stream().map(a -> {
            AssignmentDTO dto = new AssignmentDTO();
            dto.setId(a.getId());
            dto.setPersonnelId(a.getPersonnel() != null ? a.getPersonnel().getId() : null);
            dto.setAssetId(a.getAsset() != null ? a.getAsset().getId() : null);
            dto.setLocationId(a.getLocation().getId());
            dto.setAssignmentDate(a.getAssignmentDate());
            dto.setStatus(a.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}
