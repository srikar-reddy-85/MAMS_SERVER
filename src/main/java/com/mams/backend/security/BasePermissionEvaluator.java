package com.mams.backend.security;

import com.mams.backend.entity.Assignment;
import com.mams.backend.entity.Role;
import com.mams.backend.entity.Transfer;
import com.mams.backend.entity.User;
import com.mams.backend.repository.AssignmentRepository;
import com.mams.backend.repository.LocationRepository;
import com.mams.backend.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasePermissionEvaluator {

    private final LocationRepository locationRepository;
    private final AssignmentRepository assignmentRepository;
    private final TransferRepository transferRepository;

    public boolean checkBaseAccess(Authentication authentication, Long locationId) {
        User user = (User) authentication.getPrincipal();

        // Admin has access to all bases
        if (user.getRole() == Role.ADMIN) {
            return true;
        }

        // Base commander has access only to their assigned base
        if (user.getRole() == Role.BASE_COMMANDER) {
            return user.getAssignedBase() != null && user.getAssignedBase().getId().equals(locationId);
        }

        // Logistics officers have access to all bases for logistics operations
        return user.getRole() == Role.LOGISTICS_OFFICER;
    }

    public boolean checkAssignmentAccess(Authentication authentication, Long assignmentId) {
        User user = (User) authentication.getPrincipal();

        // Admin has access to all assignments
        if (user.getRole() == Role.ADMIN) {
            return true;
        }

        // Base commander has access only to assignments at their base
        if (user.getRole() == Role.BASE_COMMANDER && user.getAssignedBase() != null) {
            Assignment assignment = assignmentRepository.findById(assignmentId)
                    .orElse(null);

            if (assignment != null) {
                return assignment.getLocation().getId().equals(user.getAssignedBase().getId());
            }
        }

        return false;
    }

    public boolean checkTransferAccess(Authentication authentication, Long transferId) {
        User user = (User) authentication.getPrincipal();

        // Admin has access to all transfers
        if (user.getRole() == Role.ADMIN) {
            return true;
        }

        // Base commander has access only to transfers involving their base
        if (user.getRole() == Role.BASE_COMMANDER && user.getAssignedBase() != null) {
            Transfer transfer = transferRepository.findById(transferId)
                    .orElse(null);

            if (transfer != null) {
                Long baseId = user.getAssignedBase().getId();
                return baseId.equals(transfer.getFromLocationId()) ||
                        baseId.equals(transfer.getToLocationId());
            }
        }

        // Logistics officer has access to all transfers
        return user.getRole() == Role.LOGISTICS_OFFICER;
    }
}