package com.mams.backend.dto;

import com.mams.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private Role role;
    private Long assignedBaseId; // Only needed for BASE_COMMANDER role
}