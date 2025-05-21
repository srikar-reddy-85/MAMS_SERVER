package com.mams.backend.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDTO {
    private Long id;
    private Long assetId;
    private Long personnelId;
    private Long fromLocationId;
    private Long toLocationId;
    private LocalDate transferDate;
    private String reason;
}
