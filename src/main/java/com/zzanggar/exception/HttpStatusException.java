package com.zzanggar.exception;

import com.zzanggar.constants.ServerError;
import org.springframework.http.HttpStatus;

public class HttpStatusException extends ServiceException {
    private HttpStatus httpStatus;

    public HttpStatusException(HttpStatus httpStatus, String message) {
        super(ServerError.HTTP_ERROR, message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
