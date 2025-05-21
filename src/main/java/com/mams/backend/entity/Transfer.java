package com.mams.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assetId;

    private Long personnelId;

    private Long fromLocationId;

    private Long toLocationId;

    private LocalDate transferDate;

    private String reason;
}
