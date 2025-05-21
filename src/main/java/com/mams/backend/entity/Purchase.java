package com.mams.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which asset was purchased
    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    // Where asset was received/stored
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    private int quantity;

    private LocalDate purchaseDate;

    private String supplier;  // Optional, can be empty/null
}
