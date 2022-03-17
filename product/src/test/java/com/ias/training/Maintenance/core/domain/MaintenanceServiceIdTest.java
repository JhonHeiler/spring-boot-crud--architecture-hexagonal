package com.ias.training.Maintenance.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceServiceIdTest {

    @Test
    @DisplayName("Null maintenanceServiceId should throw an error")
    void null_maintenanceService_id_tests() {
        // A = Arrange, A = Act, A = Assert
        // Arrange
        String unsafeId = null;

        // Assert
        assertThrows(NullPointerException.class, () -> {
            // act
            new MaintenanceServiceId(unsafeId);
        });
    }



    @Test
        @DisplayName("A valid Id should not throw an error")
        void valid_id_test() {
            String validId = UUID.randomUUID().toString();
            assertDoesNotThrow(() -> {
                new MaintenanceServiceId(validId);
            });
    }

    @Test
    @DisplayName("Invalid maintenanceServiceId should throw an error")
    void invalid_maintenanceService_id_tests() {
        // A = Arrange, A = Act, A = Assert
        // Arrange
        String unsafeId = "";

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // act
            new MaintenanceServiceId(unsafeId);
        });
    }
}