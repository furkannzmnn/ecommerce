package com.base.ecommerce.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseApi {

    protected LocalDateTime timestamp;
    protected String message;
    protected String developerMessage;
    protected int statusCode;
    protected HttpStatus httpStatus;
    protected Map<?, ?> data;

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public ResponseApi(LocalDateTime timestamp, String message, String developerMessage, int statusCode, HttpStatus httpStatus, Map<?, ?> data) {
        this.timestamp = timestamp;
        this.message = message;
        this.developerMessage = developerMessage;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public ResponseApi(ResponseBuilder responseBuilder) {
        this.timestamp =responseBuilder.timesTampt;
        this.message = responseBuilder.message;
        this.developerMessage = responseBuilder.developerMessage;
        this.statusCode = responseBuilder.statusCode;
        this.httpStatus = responseBuilder.httpStatus;
        this.data = responseBuilder.data;
    }

    public static class ResponseBuilder {
        protected LocalDateTime timesTampt;
        protected String message;
        protected String developerMessage;
        protected int statusCode;
        protected HttpStatus httpStatus;
        protected Map<?, ?> data;

        public ResponseApi builder(){
            return new ResponseApi(this);
        }

        public ResponseBuilder(LocalDateTime timesTampt, String message, String developerMessage, int statusCode, HttpStatus httpStatus, Map<?, ?> data) {
            this.timesTampt = timesTampt;
            this.message = message;
            this.developerMessage = developerMessage;
            this.statusCode = statusCode;
            this.httpStatus = httpStatus;
            this.data = data;
        }

        public ResponseBuilder() {
        }

        public ResponseBuilder Message(String message) {
            this.message = message;
            return this;

        }

        public ResponseBuilder DeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;

        }

        public ResponseBuilder StatusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;

        }

        public ResponseBuilder timestamps(LocalDateTime timesTampt) {
            this.timesTampt = timesTampt;
            return this;

        }

        public ResponseBuilder HttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;

        }

        public ResponseBuilder Data(Map<?, ?> data) {
            this.data = data;
            return this;
        }

        public String getMessage() {
            return message;
        }
        }
    }

