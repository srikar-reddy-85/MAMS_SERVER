package com.mams.backend.service;

import com.mams.backend.dto.TransferDTO;
import com.mams.backend.entity.Transfer;
import com.mams.backend.repository.AssetRepository;
import com.mams.backend.repository.LocationRepository;
import com.mams.backend.repository.PersonnelRepository;
import com.mams.backend.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final AssetRepository assetRepository;
    private final PersonnelRepository personnelRepository;
    private final LocationRepository locationRepository;

    public TransferDTO createTransfer(TransferDTO dto) {
        // Validate referenced entities exist
        if (!assetRepository.existsById(dto.getAssetId())) {
            throw new IllegalArgumentException("Asset with id " + dto.getAssetId() + " not found");
        }
        if (!personnelRepository.existsById(dto.getPersonnelId())) {
            throw new IllegalArgumentException("Personnel with id " + dto.getPersonnelId() + " not found");
        }
        if (!locationRepository.existsById(dto.getFromLocationId())) {
            throw new IllegalArgumentException("From Location with id " + dto.getFromLocationId() + " not found");
        }
        if (!locationRepository.existsById(dto.getToLocationId())) {
            throw new IllegalArgumentException("To Location with id " + dto.getToLocationId() + " not found");
        }

        Transfer transfer = Transfer.builder()
                .assetId(dto.getAssetId())
                .personnelId(dto.getPersonnelId())
                .fromLocationId(dto.getFromLocationId())
                .toLocationId(dto.getToLocationId())
                .transferDate(dto.getTransferDate())
                .reason(dto.getReason())
                .build();

        Transfer saved = transferRepository.save(transfer);
        dto.setId(saved.getId());
        return dto;
    }

    public List<TransferDTO> getAllTransfers() {
        return transferRepository.findAll().stream()
                .map(t -> TransferDTO.builder()
                        .id(t.getId())
                        .assetId(t.getAssetId())
                        .personnelId(t.getPersonnelId())
                        .fromLocationId(t.getFromLocationId())
                        .toLocationId(t.getToLocationId())
                        .transferDate(t.getTransferDate())
                        .reason(t.getReason())
                        .build())
                .collect(Collectors.toList());
    }

    public TransferDTO getTransferById(Long id) {
        Transfer transfer = transferRepository.findById(id).orElseThrow();
        return TransferDTO.builder()
                .id(transfer.getId())
                .assetId(transfer.getAssetId())
                .personnelId(transfer.getPersonnelId())
                .fromLocationId(transfer.getFromLocationId())
                .toLocationId(transfer.getToLocationId())
                .transferDate(transfer.getTransferDate())
                .reason(transfer.getReason())
                .build();
    }

    public TransferDTO updateTransfer(Long id, TransferDTO dto) {
        Transfer transfer = transferRepository.findById(id).orElseThrow();

        // Validate existence on update as well
        if (!assetRepository.existsById(dto.getAssetId())) {
            throw new IllegalArgumentException("Asset with id " + dto.getAssetId() + " not found");
        }
        if (!personnelRepository.existsById(dto.getPersonnelId())) {
            throw new IllegalArgumentException("Personnel with id " + dto.getPersonnelId() + " not found");
        }
        if (!locationRepository.existsById(dto.getFromLocationId())) {
            throw new IllegalArgumentException("From Location with id " + dto.getFromLocationId() + " not found");
        }
        if (!locationRepository.existsById(dto.getToLocationId())) {
            throw new IllegalArgumentException("To Location with id " + dto.getToLocationId() + " not found");
        }

        transfer.setAssetId(dto.getAssetId());
        transfer.setPersonnelId(dto.getPersonnelId());
        transfer.setFromLocationId(dto.getFromLocationId());
        transfer.setToLocationId(dto.getToLocationId());
        transfer.setTransferDate(dto.getTransferDate());
        transfer.setReason(dto.getReason());

        Transfer updated = transferRepository.save(transfer);
        return TransferDTO.builder()
                .id(updated.getId())
                .assetId(updated.getAssetId())
                .personnelId(updated.getPersonnelId())
                .fromLocationId(updated.getFromLocationId())
                .toLocationId(updated.getToLocationId())
                .transferDate(updated.getTransferDate())
                .reason(updated.getReason())
                .build();
    }

    public void deleteTransfer(Long id) {
        transferRepository.deleteById(id);
    }
}
