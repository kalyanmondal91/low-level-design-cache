package com.github.systemdesign.cache.exception;

public class StorageFullException extends RuntimeException {
    public StorageFullException(final String message) {
        super(message);
    }
}
