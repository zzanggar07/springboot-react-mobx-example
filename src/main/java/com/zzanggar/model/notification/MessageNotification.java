package com.zzanggar.model.notification;

import com.zzanggar.constants.NotificationLevel;
import com.zzanggar.constants.NotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageNotification extends Notification {
    private Object message;

    public MessageNotification(NotificationType type, NotificationLevel level, Object message) {
        super(type, level);
        this.message = message;
    }
}



