package com.ias.training.Maintenance.infrastruture.controllers.models;

import java.time.LocalDateTime;

public class MaintenanceInput {
    private LocalDateTime startService;
    private LocalDateTime endService;
    private String description;

    public MaintenanceInput(LocalDateTime startService, LocalDateTime endService, String description) {
        this.startService = startService;
        this.endService = endService;
        this.description = description;
    }

    public LocalDateTime getStartService() {
        return startService;
    }

    public void setStartService(LocalDateTime startService) {
        this.startService = startService;
    }

    public LocalDateTime getEndService() {
        return endService;
    }

    public void setEndService(LocalDateTime endService) {
        this.endService = endService;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = description;
    }




}
