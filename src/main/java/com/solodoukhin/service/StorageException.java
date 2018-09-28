package com.solodoukhin.service;

/**
 * Author: Solodoukhin Viktor
 * Date: 21.09.18
 * Description: custom exception
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
