package com.base.ecommerce.exception.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryInvalidException extends RuntimeException{
    public CategoryInvalidException(String message) {
        super(message);
    }
}
