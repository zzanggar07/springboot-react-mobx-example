package com.zzanggar.model.notification;

import com.fasterxml.jackson.annotation.*;
import com.zzanggar.constants.NotificationLevel;
import com.zzanggar.constants.NotificationType;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", defaultImpl = Notification.class, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegisteredMessage.class, name = NotificationType.Values.REGISTERED),
        @JsonSubTypes.Type(value = RegisterMessage.class  , name = NotificationType.Values.REGISTER),
})
public class Notification {
    private NotificationType  type;
    private NotificationLevel level;

    @JsonCreator
    public Notification(@JsonProperty("type") NotificationType type) {
        this(type, NotificationLevel.INFO);
    }

    protected Notification(NotificationType type, NotificationLevel level) {
        this.type  = type;
        this.level = level;
    }
}



