//package com.mams.backend.controller;
//
//import com.mams.backend.dto.PurchaseDTO;
//import com.mams.backend.service.PurchaseService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/purchases")
//@RequiredArgsConstructor
//public class PurchaseController {
//
//    private final PurchaseService purchaseService;
//
//    @PostMapping
//    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseDTO dto) {
//        PurchaseDTO saved = purchaseService.createPurchase(dto);
//        return ResponseEntity.ok(saved);
//    }
//}


package com.mams.backend.controller;

import com.mams.backend.dto.PurchaseDTO;
import com.mams.backend.security.CheckBaseAccess;
import com.mams.backend.security.LogisticsOfficerOnly;
import com.mams.backend.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    @LogisticsOfficerOnly
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseDTO dto) {
        PurchaseDTO saved = purchaseService.createPurchase(dto);
        return ResponseEntity.ok(saved);
    }

//    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
//    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
//        return ResponseEntity.ok(purchaseService.getAllPurchases());
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
//    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long id) {
//        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
//    }
//
//    @GetMapping("/location/{locationId}")
//    @CheckBaseAccess
//    public ResponseEntity<List<PurchaseDTO>> getPurchasesByLocation(@PathVariable Long locationId) {
//        return ResponseEntity.ok(purchaseService.getPurchasesByLocation(locationId));
//    }
//
//    @GetMapping("/asset/{assetId}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
//    public ResponseEntity<List<PurchaseDTO>> getPurchasesByAsset(@PathVariable Long assetId) {
//        return ResponseEntity.ok(purchaseService.getPurchasesByAsset(assetId));
//    }
//
//    @GetMapping("/date")
//    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
//    public ResponseEntity<List<PurchaseDTO>> getPurchasesByDateRange(
//            @RequestParam LocalDate startDate,
//            @RequestParam LocalDate endDate) {
//        return ResponseEntity.ok(purchaseService.getPurchasesByDateRange(startDate, endDate));
//    }
//
//    @PutMapping("/{id}")
//    @LogisticsOfficerOnly
//    public ResponseEntity<PurchaseDTO> updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO dto) {
//        return ResponseEntity.ok(purchaseService.updatePurchase(id, dto));
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
//        purchaseService.deletePurchase(id);
//        return ResponseEntity.noContent().build();
//    }
}