package com.ias.training.Maintenance.core.domain;
import org.apache.commons.lang3.Validate;
public class MaintenanceServiceId {
    private final String value;

    public MaintenanceServiceId(String value) {
        Validate.notNull(value,  "maintenance id can not be null.");
        Validate.isTrue(value.trim().length() == 36, "maintenance id must be equal to 36 characters");
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
