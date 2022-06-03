package com.base.ecommerce.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class StopWordFilter implements Filter {

    private static final String[] stopWords = new String[9];
    private final Logger logger = LoggerFactory.getLogger(StopWordFilter.class);


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

        if (Arrays.stream(getStopWords()).anyMatch(requestURI::contains)) {
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

    private String[] getStopWords() {
        return stopWords;
    }

    static {
        stopWords[0] = "/xss";
        stopWords[1] = "/drop-index";
        stopWords[2] = "/drop-view";
        stopWords[3] = "/drop-procedure";
        stopWords[4] = "/drop-function";
        stopWords[5] = "/drop-trigger";
        stopWords[6] = "/drop-user";
        stopWords[7] = "/drop-role";
        stopWords[8] = "/drop-schema";
    }
}
