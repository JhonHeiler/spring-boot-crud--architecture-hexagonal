package com.ias.training.Maintenance.core.gateways;

import com.ias.training.Maintenance.core.domain.MaintenanceService;
import com.ias.training.Maintenance.core.domain.MaintenanceServiceId;
import com.ias.training.Maintenance.shared.domain.PageQuery;

import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository {
    List<MaintenanceService> query(PageQuery pageQuery);

    Optional<MaintenanceService> get(MaintenanceServiceId maintenanceServiceId);

    void create(MaintenanceService maintenanceService);
    void update(MaintenanceService maintenanceService);

    void delete(MaintenanceServiceId maintenanceServiceId);

}
