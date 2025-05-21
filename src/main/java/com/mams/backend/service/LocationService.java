package com.mams.backend.service;

import com.mams.backend.dto.LocationDTO;
import com.mams.backend.entity.Location;
import com.mams.backend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationDTO createLocation(LocationDTO dto) {
        Location location = Location.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .region(dto.getRegion())
                .build();
        Location saved = locationRepository.save(location);

        // Return a new DTO with ID set, do NOT modify the input dto
        return LocationDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .region(saved.getRegion())
                .build();
    }
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(loc -> LocationDTO.builder()
                        .id(loc.getId())
                        .name(loc.getName())
                        .description(loc.getDescription())
                        .region(loc.getRegion())
                        .build())
                .collect(Collectors.toList());
    }

    public LocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id).orElseThrow();
        return LocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .description(location.getDescription())
                .region(location.getRegion())
                .build();
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public LocationDTO updateLocation(Long id, LocationDTO dto) {
        Location location = locationRepository.findById(id).orElseThrow();
        location.setName(dto.getName());
        location.setDescription(dto.getDescription());
        location.setRegion(dto.getRegion());
        Location updated = locationRepository.save(location);
        return LocationDTO.builder()
                .id(updated.getId())
                .name(updated.getName())
                .description(updated.getDescription())
                .region(updated.getRegion())
                .build();
    }
}
