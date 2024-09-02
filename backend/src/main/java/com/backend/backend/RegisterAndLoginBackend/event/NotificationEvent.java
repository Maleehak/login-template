package com.backend.backend.RegisterAndLoginBackend.event;

import enums.NotificationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

@Getter
@Setter
public class NotificationEvent extends ApplicationEvent {
    String to;
    NotificationType type;
    Map<String, String> additional;

    public NotificationEvent(Object source, NotificationType type, String to, Map<String, String> additional) {
        super(source);
        this.type = type;
        this.to = to;
        this.additional = additional;
    }
}
