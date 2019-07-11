package com.moses.boot.sample.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class DefaultHandlerInterceptor implements HandlerInterceptor {
    private static Logger LOG = LoggerFactory.getLogger(DefaultHandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.debug("Request Header Accept: " + request.getHeader("accept"));
        LOG.debug("Handler object: " + handler);
        return true;
    }
}
