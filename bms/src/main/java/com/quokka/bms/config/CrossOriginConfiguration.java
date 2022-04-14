package com.quokka.bms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@CrossOrigin
public class CrossOriginConfiguration implements Filter {

    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN";

    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS";

    private static final String ALLOWED_ORIGIN = "*";

    private static final Long MAX_AGE = 5600L;

    private static Logger log = LoggerFactory.getLogger(CrossOriginConfiguration.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.addHeader("Access-Control-Allow-Headers", ALLOWED_ORIGIN);
        response.addHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);

        if (!request.getRequestURI().contains("download"))
            response.addHeader("Content-Type", "application/json");


        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
            response.setStatus(HttpServletResponse.SC_OK);
            log.info("Option request is completed");
            return;
        }
        
       

        if (!HttpMethod.OPTIONS.matches(request.getMethod()) && !response.containsHeader("Access-Control-Allow-Origin")) {
            log.info("Cors header is fixed");
            response.addHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
