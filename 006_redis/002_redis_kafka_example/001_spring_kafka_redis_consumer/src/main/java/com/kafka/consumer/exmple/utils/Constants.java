package com.kafka.consumer.exmple.utils;

public final class Constants {

    public static final String TOPIC_NAME = "news";
    public static final String MESSAGE_GROUP_NAME = "news";

    private Constants() {
        throw new UnsupportedOperationException("Esta es una clase de utilidades y no debe ser instanciada.");
    }
}