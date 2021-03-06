package com.ramiaslan.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CategoryException(String message) {
        super(message);
    }

}
