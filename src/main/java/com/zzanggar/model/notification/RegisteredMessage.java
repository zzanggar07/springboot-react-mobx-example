package com.zzanggar.model.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zzanggar.constants.NotificationType;

public class RegisteredMessage extends Notification {
    @JsonCreator
    public RegisteredMessage() {
        super(NotificationType.REGISTERED);
    }
}
