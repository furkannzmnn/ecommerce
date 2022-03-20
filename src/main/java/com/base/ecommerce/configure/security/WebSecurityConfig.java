package com.base.ecommerce.configure.security;

import com.base.ecommerce.filter.StopWordFilter;
import com.base.ecommerce.security.CustomAuthenticationProvider;
import com.base.ecommerce.security.UserDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomAuthenticationProvider authenticationProvider;
    private final UserDetailService userDetailService;

    public WebSecurityConfig(CustomAuthenticationProvider authenticationProvider, UserDetailService userDetailService) {
        this.authenticationProvider = authenticationProvider;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userDetailService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and()
                .addFilterAfter(new StopWordFilter(), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }



}
