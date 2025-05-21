package com.mams.backend.controller;

import com.mams.backend.dto.PurchaseDTO;
import com.mams.backend.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseDTO dto) {
        PurchaseDTO saved = purchaseService.createPurchase(dto);
        return ResponseEntity.ok(saved);
    }
}
