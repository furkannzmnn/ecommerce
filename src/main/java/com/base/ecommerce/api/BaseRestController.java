package com.base.ecommerce.api;

import com.base.ecommerce.exception.GenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseRestController {

    private static final Logger logger = LoggerFactory.getLogger(BaseRestController.class);

    private final MappingJackson2JsonView view = new MappingJackson2JsonView();

    @PostConstruct
    public void init() {
        view.setExtractValueFromSingleKeyModel(true);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest request, Throwable throwable) {

        logger.error("Error occured in request: " + request.getRequestURL(), throwable);
        if (throwable instanceof IllegalArgumentException) {
            IllegalArgumentException ex = (IllegalArgumentException) throwable;
            logger.error("Error: " + ex.getMessage());
            return new ModelAndView(view, "error", ex.getMessage());
        } else if (throwable instanceof RuntimeException) {
            Exception ex = (Exception) throwable;
            logger.error("Error: " + ex.getMessage());
            return new ModelAndView(view, "error", ex.getMessage());
        } else {
            logger.error("Error: " + throwable.getMessage());
            return new ModelAndView(view, "error", throwable.getMessage());
        }
    }

    @ExceptionHandler(value={GenericException.class})
    protected ResponseEntity<Object> systemHandle(
            GenericException ex, WebRequest request) {
        return new ResponseEntity<>(ex, new HttpHeaders(), ex.getStatus());
    }

}
