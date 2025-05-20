package com.mams.backend.service;

import com.mams.backend.dto.AssetDTO;
import com.mams.backend.entity.Asset;
import com.mams.backend.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public Asset saveAsset(AssetDTO dto) {
        Asset asset = Asset.builder()
                .name(dto.getName())
                .type(dto.getType())
                .status(dto.getStatus())
                .quantity(dto.getQuantity())
                .build();
        return assetRepository.save(asset);
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Asset getAssetById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
