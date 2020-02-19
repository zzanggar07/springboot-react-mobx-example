package com.zzanggar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(annotations = {Controller.class})
public class WebControllerAdvise {
    @ExceptionHandler(value = PageNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Page Not Found")
    public String handlePageNotFoundException(PageNotFoundException ex) {
        return "error";
    }
}
