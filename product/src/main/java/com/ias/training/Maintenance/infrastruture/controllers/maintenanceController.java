package com.ias.training.Maintenance.infrastruture.controllers;



import com.ias.training.Maintenance.infrastruture.controllers.models.MaintenanceDTO;
import com.ias.training.Maintenance.infrastruture.controllers.models.MaintenanceInput;


import com.ias.training.Maintenance.infrastruture.controllers.services.MaintenanceServices;

import com.ias.training.Maintenance.shared.domain.errors.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class maintenanceController {
    private final MaintenanceServices services;

    public maintenanceController(MaintenanceServices services) {
        this.services = services;
    }


    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public ResponseEntity<?> createMaintenance(@RequestBody MaintenanceInput maintenanceInput) {
        try {
            MaintenanceDTO maintenanceService = services.createService(maintenanceInput);
            return ResponseEntity.ok(maintenanceService);
        } catch (IllegalArgumentException | NullPointerException e) {
            ApplicationError error = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of(
                            "error", e.getMessage()
                    )
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        } catch (Exception e) {
            ApplicationError error = new ApplicationError(
                    "SystemError",
                    e.getMessage(),
                    Map.of()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    @RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMaintenance(@PathVariable("id") String maintenanceId) {
        Optional<MaintenanceDTO> product = services.getService(maintenanceId);
        if(product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            ApplicationError error = new ApplicationError(
                    "ResourceNotFound",
                    "Service with id not found",
                    Map.of("id", maintenanceId)
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public ResponseEntity<List<MaintenanceDTO>> listService(
            @RequestParam(name = "skip", defaultValue = "0") Integer skip,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ) {
        try {
        return ResponseEntity.ok(services.queryServices(skip, limit));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @RequestMapping(value = "/service/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> cupdateService(@PathVariable("id") String maintenanceId, @RequestBody MaintenanceInput maintenanceInput) {
        try {
            MaintenanceDTO maintenanceService = services.updateService(maintenanceId,maintenanceInput);
            return ResponseEntity.ok(maintenanceService);
        } catch (IllegalArgumentException | NullPointerException e) {
            ApplicationError error = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of(
                            "error", e.getMessage()
                    )
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        } catch (Exception e) {
            ApplicationError error = new ApplicationError(
                    "SystemError",
                    e.getMessage(),
                    Map.of()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }
    @RequestMapping(value = "service/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String maintenanceId
    ) {
        try {
            services.delete(maintenanceId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
