package com.ias.training.Maintenance.infrastruture.controllers.services;

import com.ias.training.Maintenance.core.domain.MaintenanceService;
import com.ias.training.Maintenance.core.domain.MaintenanceServiceDescription;
import com.ias.training.Maintenance.core.domain.MaintenanceServiceId;
import com.ias.training.Maintenance.core.gateways.MaintenanceRepository;
import com.ias.training.Maintenance.infrastruture.controllers.models.MaintenanceDTO;
import com.ias.training.Maintenance.infrastruture.controllers.models.MaintenanceInput;
import com.ias.training.Maintenance.shared.domain.Limit;
import com.ias.training.Maintenance.shared.domain.PageQuery;
import com.ias.training.Maintenance.shared.domain.Skip;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaintenanceServices {
    private final MaintenanceRepository repository;

    public MaintenanceServices(MaintenanceRepository repository) {
        this.repository = repository;
    }

    public MaintenanceDTO createService(MaintenanceInput maintenanceInput) {
        String value = UUID.randomUUID().toString();
        try {
            MaintenanceService  maintenanceService = new MaintenanceService(
                    new MaintenanceServiceId(value),
                    maintenanceInput.getStartService(),
                    maintenanceInput.getEndService(),
                    new MaintenanceServiceDescription(maintenanceInput.getDescripcion())
            );
            repository.create(maintenanceService);

            return MaintenanceDTO.fromDomain(maintenanceService);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Error validating servicio data", e);
        }
    }

    public List<MaintenanceDTO> queryServices(Integer skip, Integer limit) {
        PageQuery pageQuery = new PageQuery(
                new Skip(skip),
                new Limit(limit)
        );
        List<MaintenanceService> maintenanceServices = repository.query(pageQuery);

        List<MaintenanceDTO> result = new ArrayList<>();
        for (MaintenanceService maintenanceService : maintenanceServices) {
            MaintenanceDTO dto = MaintenanceDTO.fromDomain(maintenanceService);
            result.add(dto);
        }
        return result;
    }


    public MaintenanceDTO updateService(String maintenanceServiceId, MaintenanceInput maintenanceInput) {

        try {
            MaintenanceService  maintenanceService = new MaintenanceService(
                    new MaintenanceServiceId(maintenanceServiceId),
                    maintenanceInput.getStartService(),
                    maintenanceInput.getEndService(),
                    new MaintenanceServiceDescription(maintenanceInput.getDescripcion())
            );
            repository.create(maintenanceService);

            return MaintenanceDTO.fromDomain(maintenanceService);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Error validating servicio data", e);
        }
    }

    public Optional<MaintenanceDTO> getService(String maintenanceServiceId) {
        return repository.get(new MaintenanceServiceId(maintenanceServiceId))
                .map(maintenanceService -> {
                    return MaintenanceDTO.fromDomain(maintenanceService);
                });

    }
    public void delete(String maintenanceServiceId) {
        repository.delete(new MaintenanceServiceId(maintenanceServiceId));
    }
}
