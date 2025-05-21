package com.mams.backend.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AssignmentDTO {
    private Long id;
    private Long personnelId;
    private Long assetId;
    private Long locationId;
    private LocalDate assignmentDate;
    private String status;
}
