package com.base.ecommerce.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StopWordFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(StopWordFilter.class);
    private final Set<String> stopWords = getStopWords();


    public StopWordFilter() {
        logger.info("StopWordFilter constructor");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("StopWordFilter init {}", filterConfig.getServletContext().getServerInfo());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (stopWords.stream().anyMatch(requestURI::contains)) {
            logger.info("StopWordFilter: {}", requestURI);
            response.getWriter().println("YOUR ACCESS IS DENIED");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        logger.info("StopWordFilter destroy");
    }

    private Set<String> getStopWords() {
        Set<String> stopWords = new HashSet<>();
        stopWords.add("/xss");
        stopWords.add("/sql");
        stopWords.add("/robots.txt");
        stopWords.add("/password");
        stopWords.add("/hacked");
        stopWords.add("/jni");
        stopWords.add("/inject");
        stopWords.add("/drop-table");
        stopWords.add("/drop-database");
        stopWords.add("/drop-column");
        stopWords.add("/drop-index");
        stopWords.add("/drop-view");
        stopWords.add("/drop-procedure");
        stopWords.add("/drop-function");
        stopWords.add("/drop-trigger");
        stopWords.add("/drop-user");
        stopWords.add("/drop-role");
        stopWords.add("/drop-schema");
        return stopWords;
    }
}
