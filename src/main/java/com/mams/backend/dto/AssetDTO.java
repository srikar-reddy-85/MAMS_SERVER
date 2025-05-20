package com.mams.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDTO {
    private String name;
    private String type;
    private String status;
    private int quantity;
}
