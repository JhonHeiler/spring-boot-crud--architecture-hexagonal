package com.ias.training.Maintenance.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceServiceDescriptionTest {
    @Test
    @DisplayName("A valid Id should not throw an error")
    void valid_id_test() {
        String validId = "mi hermano";
        assertDoesNotThrow(() -> {
            new MaintenanceServiceDescription(validId);
        });
    }

}