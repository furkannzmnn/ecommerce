package com.base.ecommerce.exception.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FavoriteAlreadyException extends RuntimeException{
    public FavoriteAlreadyException(String message) {
        super(message);
    }
}
