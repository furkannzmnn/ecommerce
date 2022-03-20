package com.base.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SessionManager {

    @Autowired
    private  FindByIndexNameSessionRepository repository;

    public void saveSession(String sessionId, String username) {
        Session session = repository.findById(sessionId);
        session.setAttribute("username", username);
        repository.save(session);
    }

    public String getUsername(String sessionId) {
        Session session = repository.findById(sessionId);
        return (String) session.getAttribute("username");
    }

    public void deleteSession(String sessionId) {
        repository.deleteById(sessionId);
    }

    public Map findByPrincipalName() {
      return repository.findByPrincipalName("admin");
    }


}
