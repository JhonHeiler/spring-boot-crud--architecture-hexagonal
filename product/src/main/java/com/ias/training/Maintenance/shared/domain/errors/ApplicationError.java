package com.ias.training.Maintenance.shared.domain.errors;

import java.util.Map;

public class ApplicationError {
    private final String errorCode;
    private final String message;
    private final Map<String, String> metadata;

    public ApplicationError(String errorCode, String message, Map<String, String> metadata) {
        this.errorCode = errorCode;
        this.message = message;
        this.metadata = metadata;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}
