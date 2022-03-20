package com.base.ecommerce.api;

import com.base.ecommerce.security.SessionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController extends BaseRestController{

    private final SessionManager sessionManager;

    public SessionController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getSession() {
        return ResponseEntity.ok(sessionManager.findByPrincipalName());
    }
}
