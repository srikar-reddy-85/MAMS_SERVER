//package com.mams.backend.controller;
//
//import com.mams.backend.dto.AssetDTO;
//import com.mams.backend.entity.Asset;
//import com.mams.backend.service.AssetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/assets")
//public class AssetController {
//
//    @Autowired
//    private AssetService assetService;
//
//    @PostMapping
//    public Asset createAsset(@RequestBody AssetDTO assetDTO) {
//        return assetService.saveAsset(assetDTO);
//    }
//
//    @GetMapping
//    public List<Asset> getAllAssets() {
//        return assetService.getAllAssets();
//    }
//
//    @GetMapping("/{id}")
//    public Asset getAssetById(@PathVariable Long id) {
//        return assetService.getAssetById(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAsset(@PathVariable Long id) {
//        assetService.deleteAsset(id);
//    }
//}

package com.mams.backend.controller;

import com.mams.backend.dto.AssetDTO;
import com.mams.backend.entity.Asset;
import com.mams.backend.security.AdminOnly;
import com.mams.backend.security.CheckBaseAccess;
import com.mams.backend.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    @AdminOnly
    public ResponseEntity<Asset> createAsset(@RequestBody AssetDTO assetDTO) {
        return ResponseEntity.ok(assetService.saveAsset(assetDTO));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

//    @GetMapping("/location/{locationId}")
//    @CheckBaseAccess
//    public ResponseEntity<List<Asset>> getAssetsByLocation(@PathVariable Long locationId) {
//        return ResponseEntity.ok(assetService.getAssetsByLocation(locationId));
//    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetById(id));
    }

//    @PutMapping("/{id}")
//    @AdminOnly
//    public ResponseEntity<Asset> updateAsset(@PathVariable Long id, @RequestBody AssetDTO assetDTO) {
//        return ResponseEntity.ok(assetService.updateAsset(id, assetDTO));
//    }

    @DeleteMapping("/{id}")
    @AdminOnly
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
