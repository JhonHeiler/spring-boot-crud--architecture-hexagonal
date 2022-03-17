package com.ias.training.Maintenance.core.domain;

import org.apache.commons.lang3.Validate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MaintenanceService {
    private final MaintenanceServiceId id;
    private final LocalDateTime startService;
    private final LocalDateTime endService;
    private final MaintenanceServiceDescription descripcion;

    public MaintenanceService(MaintenanceServiceId id, LocalDateTime startService, LocalDateTime endService, MaintenanceServiceDescription descripcion) {
        this.id = Validate.notNull(id);
        this.startService = Validate.notNull(startService);
        Validate.isTrue(LocalDateTime.now().isAfter(startService), "The date must not be in the future");
        this.endService = Validate.notNull(endService);
        Validate.isTrue(LocalDateTime.now().isAfter(endService) && endService.isBefore(LocalDateTime.now()), "it cannot represent a date before the start date, nor can it represent a date and time in the future.");
        this.descripcion = Validate.notNull(descripcion);
    }

    public MaintenanceServiceId getId() {
        return id;
    }

    public LocalDateTime getStartService() {
        return startService;
    }

    public LocalDateTime getEndService() {
        return endService;
    }

    public MaintenanceServiceDescription getDescripcion() {
        return descripcion;
    }
}
