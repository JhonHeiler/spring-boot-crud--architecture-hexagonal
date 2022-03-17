package com.ias.training.Maintenance.shared.domain;

import org.apache.commons.lang3.Validate;

public class Limit {
    private final Integer value;
    public Limit(Integer value) {
        Validate.notNull(value, "Limit can not be null.");
        Validate.inclusiveBetween(1, 100, value, "Limit should be between 1 and 100.");
        this.value = value;
    }
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
