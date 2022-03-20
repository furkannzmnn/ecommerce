package com.base.ecommerce.exception.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiscountedInvalidException extends RuntimeException{

    public DiscountedInvalidException(String message) {
        super(message);
    }
}
