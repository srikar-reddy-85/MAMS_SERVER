package com.mams.backend.service;

import com.mams.backend.dto.PersonnelDTO;
import com.mams.backend.entity.Personnel;
import com.mams.backend.repository.PersonnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    private final PersonnelRepository repository;

    public Personnel addPersonnel(PersonnelDTO dto) {
        Personnel personnel = Personnel.builder()
                .name(dto.getName())
                .rank(dto.getRank())
                .unit(dto.getUnit())
                .contactInfo(dto.getContactInfo())
                .build();
        return repository.save(personnel);
    }

    public List<Personnel> getAll() {
        return repository.findAll();
    }

    public Personnel getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Personnel not found"));
    }

    public Personnel update(Long id, PersonnelDTO dto) {
        Personnel personnel = getById(id);
        personnel.setName(dto.getName());
        personnel.setRank(dto.getRank());
        personnel.setUnit(dto.getUnit());
        personnel.setContactInfo(dto.getContactInfo());
        return repository.save(personnel);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
