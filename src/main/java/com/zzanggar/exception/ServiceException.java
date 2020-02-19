package com.zzanggar.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zzanggar.constants.ServerError;

@JsonIgnoreProperties({"stackTrace", "cause", "localizedMessage", "suppressed", "suppressedExceptions"})
public class ServiceException extends Exception {
    private ServerError error;

    public ServiceException(ServerError error) {
        super(error.getDesc());
        this.error = error;
    }

    public ServiceException(ServerError error, String message) {
        super(String.format("%s (%s)", error.getDesc(), message));
        this.error = error;
    }

    public int getCode() {
        return error.getCode();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getDetailMessage() {
        return super.getMessage();
    }
}
