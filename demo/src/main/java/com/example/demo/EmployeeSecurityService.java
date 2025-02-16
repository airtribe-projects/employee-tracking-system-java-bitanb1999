package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSecurityService {

    public boolean isOwner(Authentication authentication, Long employeeId) {
        // Assuming the principal has an ID field
        Long authenticatedUserId = (Long) authentication.getPrincipal();
        return authenticatedUserId.equals(employeeId);
    }
}
