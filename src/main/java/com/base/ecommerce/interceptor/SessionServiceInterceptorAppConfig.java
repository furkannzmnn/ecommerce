package com.base.ecommerce.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class SessionServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {

    private final SessionServiceInterceptor productServiceInterceptor;
    private final AuthenticateInterceptor authenticateInterceptor;

    public SessionServiceInterceptorAppConfig(SessionServiceInterceptor productServiceInterceptor, AuthenticateInterceptor authenticateInterceptor) {
        this.productServiceInterceptor = productServiceInterceptor;
        this.authenticateInterceptor = authenticateInterceptor;
    }

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInterceptor);
        registry.addInterceptor(authenticateInterceptor);
    }
}
