package com.zzanggar.model.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zzanggar.constants.NotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterMessage extends Notification {
    private long memberIdx;

    @JsonCreator
    public RegisterMessage() {
        super(NotificationType.REGISTER);
    }
}
