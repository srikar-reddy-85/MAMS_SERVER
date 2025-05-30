//package com.mams.backend.controller;
//
//import com.mams.backend.dto.TransferDTO;
//import com.mams.backend.service.TransferService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/transfers")
//@RequiredArgsConstructor
//public class TransferController {
//
//    private final TransferService transferService;
//
//    @PostMapping
//    public ResponseEntity<TransferDTO> createTransfer(@RequestBody TransferDTO dto) {
//        TransferDTO created = transferService.createTransfer(dto);
//        return ResponseEntity.ok(created);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TransferDTO>> getAllTransfers() {
//        return ResponseEntity.ok(transferService.getAllTransfers());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TransferDTO> getTransferById(@PathVariable Long id) {
//        return ResponseEntity.ok(transferService.getTransferById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TransferDTO> updateTransfer(@PathVariable Long id, @RequestBody TransferDTO dto) {
//        return ResponseEntity.ok(transferService.updateTransfer(id, dto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
//        transferService.deleteTransfer(id);
//        return ResponseEntity.noContent().build();
//    }
//}


// Enhanced TransferController.java with RBAC
package com.mams.backend.controller;

import com.mams.backend.dto.TransferDTO;
import com.mams.backend.security.AdminOnly;
import com.mams.backend.security.CheckBaseAccess;
import com.mams.backend.security.LogisticsOfficerOnly;
import com.mams.backend.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @LogisticsOfficerOnly
    public ResponseEntity<TransferDTO> createTransfer(@RequestBody TransferDTO dto) {
        TransferDTO created = transferService.createTransfer(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
    public ResponseEntity<List<TransferDTO>> getAllTransfers() {
        return ResponseEntity.ok(transferService.getAllTransfers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'BASE_COMMANDER', 'LOGISTICS_OFFICER')")
    public ResponseEntity<TransferDTO> getTransferById(@PathVariable Long id) {
        return ResponseEntity.ok(transferService.getTransferById(id));
    }

//    @GetMapping("/location/{locationId}")
//    @CheckBaseAccess
//    public ResponseEntity<List<TransferDTO>> getTransfersByLocation(@PathVariable Long locationId) {
//        return ResponseEntity.ok(transferService.getTransfersByLocation(locationId));
//    }

    @PutMapping("/{id}")
    @LogisticsOfficerOnly
    public ResponseEntity<TransferDTO> updateTransfer(@PathVariable Long id, @RequestBody TransferDTO dto) {
        return ResponseEntity.ok(transferService.updateTransfer(id, dto));
    }

    @DeleteMapping("/{id}")
    @AdminOnly
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long id) {
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }
}