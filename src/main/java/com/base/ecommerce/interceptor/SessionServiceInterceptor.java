package com.base.ecommerce.interceptor;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpSession;

@Component
public class SessionServiceInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(SessionServiceInterceptor.class);
    private final HttpSession httpSession;

    public SessionServiceInterceptor(HttpSession httpSession) {
        this.httpSession = httpSession;
    }


    @Override
    public boolean preHandle(@NotNull javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         if (authentication == null) {
            return true;
        }
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            logger.info("User " + authentication.getName() + " is admin");
            logger.error("request URL : " + request.getRequestURL());

            long startTime = System.currentTimeMillis();
            httpSession.setAttribute("executionTime", startTime);
            logger.info("Time since last request in this session: {} ms", startTime);

            if (System.currentTimeMillis() - request.getSession().getLastAccessedTime() > 1000 * 60 * 60) {
                logger.error("Session expired");
                SecurityContextHolder.clearContext();
                request.logout();
                response.sendRedirect("/login");
                return false;
            }

        }
        return true;
    }

}
