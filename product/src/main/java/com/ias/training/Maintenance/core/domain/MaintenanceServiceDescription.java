package com.ias.training.Maintenance.core.domain;
import org.apache.commons.lang3.Validate;
public class MaintenanceServiceDescription {
    private final String value;

    public MaintenanceServiceDescription(String value) {
        Validate.notBlank(value,  "maintenance description can not be null.");
        Validate.isTrue(value.trim().length() < 512, "maintenance description cannot be greater than 512");
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
