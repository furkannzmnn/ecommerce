package com.base.ecommerce.exception;

import com.base.ecommerce.dto.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GenericException extends RuntimeException {


    private final String message;
    private final HttpStatus status;
    private final int statusCode;
    private final Map<?,?> data;
    private final ErrorCode errorCode;

    public GenericException(String message, HttpStatus status, Integer statusCode,
                            Map<?, ?> data, ErrorCode errorCode) {
        Map<?, ?> data1;

        data1 = data;
        if (data == null) {
            data1 = new HashMap<>();}
        this.data = data1;
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }


    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String message;
        private HttpStatus status;
        private int statusCode;
        private Map<?,?> data;
        private ErrorCode errorCode;

        Builder() {}


        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder data(Map<?, ?> data) {
            this.data = data;
            return this;
        }

        public Builder errorCode(ErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public GenericException build() {
            return new GenericException(message,status,statusCode,data,errorCode);
        }
    }
}
