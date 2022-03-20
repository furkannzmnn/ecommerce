package com.base.ecommerce.security;

import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Locale;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final LocaleResolver localeResolver;

    public CustomAuthenticationFailureHandler(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @Override
    public void onAuthenticationFailure(final javax.servlet.http.HttpServletRequest request,
                                        final javax.servlet.http.HttpServletResponse response,
                                        final org.springframework.security.core.AuthenticationException exception) throws ServletException, IOException {
        setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
        final Locale locale = localeResolver.resolveLocale(request);
        String errorMessage = "";

        if (exception.getMessage().equals("Bad credentials")) {
            errorMessage = "OPS! Wrong username or password. Please try again.";
        }
        else if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = "OPS! Your account is disabled. Please contact the administrator.";
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = "OPS! Your account has expired. Please contact the administrator.";
        }

        response.setStatus(401);
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);

    }
}
