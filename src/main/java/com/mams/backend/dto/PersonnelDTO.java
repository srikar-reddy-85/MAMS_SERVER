package com.mams.backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonnelDTO {
    private String name;
    private String rank;
    private String unit;
    private String contactInfo;
}
