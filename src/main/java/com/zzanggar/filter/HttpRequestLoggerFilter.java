package com.zzanggar.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Log4j2
public class HttpRequestLoggerFilter extends CommonsRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        log.info(message);
    }
}
