package com.zzanggar.exception;

import org.springframework.http.HttpStatus;

public class PageNotFoundException extends Exception {
    public PageNotFoundException() {
        super(HttpStatus.NOT_FOUND.name());
    }
}
