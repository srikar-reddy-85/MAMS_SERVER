package com.mams.backend.controller;

import com.mams.backend.dto.AssetDTO;
import com.mams.backend.entity.Asset;
import com.mams.backend.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping
    public Asset createAsset(@RequestBody AssetDTO assetDTO) {
        return assetService.saveAsset(assetDTO);
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
    }
}
