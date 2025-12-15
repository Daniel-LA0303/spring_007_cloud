package com.kafka.consumer.exmple.exceptions;

import org.springframework.http.HttpStatus;

public class ExternalApiException extends RuntimeException {

    private final HttpStatus status;
    private final String responseBody;

    public ExternalApiException(HttpStatus status, String responseBody) {
        super("Error al consumir API externa - Status: " + status + ", Body: " + responseBody);
        this.status = status;
        this.responseBody = responseBody;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
