package com.example.demo.exception;

/**
 * Custom exception class for handling product-related errors.
 */
public class ProductException extends RuntimeException {

    /**
     * Constructs a new ProductException with the specified detail message.
     *
     * @param message the detail message
     */
    public ProductException(String message) {
        super(message);
    }
}