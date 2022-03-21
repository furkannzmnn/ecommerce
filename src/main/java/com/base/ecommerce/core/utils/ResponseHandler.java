
package com.base.ecommerce.core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Deprecated(since = "1.0.0")
public class ResponseHandler {

    private ResponseHandler() {
    }

    public static ResponseEntity<Object> jsonGenerateResponse(String messageDetails,
                                                              HttpStatus httpStatus,
                                                              Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message", messageDetails);
        map.put("status", httpStatus.value());
        map.put("data", responseObj);
        return new ResponseEntity<>(map, httpStatus);
    }


}
