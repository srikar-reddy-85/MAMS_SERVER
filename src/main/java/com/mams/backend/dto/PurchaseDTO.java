package com.mams.backend.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDTO {
    private Long assetId;
    private Long locationId;
    private int quantity;
    private LocalDate purchaseDate;
    private String supplier;
}
