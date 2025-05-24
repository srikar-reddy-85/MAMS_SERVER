package com.mams.backend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDTO {
    private Long id;
    private Long assetId;
    private Long locationId;
    private int quantity;
    private LocalDate purchaseDate;
    private String supplier;
}
