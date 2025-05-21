package com.mams.backend.service;

import com.mams.backend.dto.PurchaseDTO;
import com.mams.backend.entity.Asset;
import com.mams.backend.entity.Location;
import com.mams.backend.entity.Purchase;
import com.mams.backend.repository.AssetRepository;
import com.mams.backend.repository.LocationRepository;
import com.mams.backend.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final AssetRepository assetRepository;
    private final LocationRepository locationRepository;

    public PurchaseDTO createPurchase(PurchaseDTO dto) {
        Asset asset = assetRepository.findById(dto.getAssetId())
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        Location location = locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        // Check if enough quantity is available
        if (asset.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Not enough quantity available. Required: "
                    + dto.getQuantity() + ", Available: " + asset.getQuantity());
        }

        // Subtract the quantity and update asset
        int newQuantity = asset.getQuantity() - dto.getQuantity();
        asset.setQuantity(newQuantity);
        assetRepository.save(asset);

        // Save purchase record
        Purchase purchase = Purchase.builder()
                .asset(asset)
                .location(location)
                .quantity(dto.getQuantity())
                .purchaseDate(dto.getPurchaseDate())
                .supplier(dto.getSupplier())
                .build();

        purchaseRepository.save(purchase);

        // Return updated DTO
        return PurchaseDTO.builder()
                .assetId(asset.getId())
                .locationId(location.getId())
                .quantity(dto.getQuantity())
                .purchaseDate(dto.getPurchaseDate())
                .supplier(dto.getSupplier())
                .build();
    }

}
