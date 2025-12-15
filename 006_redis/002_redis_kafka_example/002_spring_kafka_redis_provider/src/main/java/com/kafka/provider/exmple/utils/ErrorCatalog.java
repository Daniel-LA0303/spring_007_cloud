package com.kafka.provider.exmple.utils;

public enum ErrorCatalog {
	
	INVALID_PARAMETERS("NEWS_MS_001", "Invalid date request param."),
    INTERNAL_SERVER_ERROR("NEWS_MS_002", "Internal server error.");

    private final String code;
    private final String message;

    // Constructor explícito
    private ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getters manuales
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
