package com.thomasdedinsky.fydp.fydpweb.models;

import org.springframework.context.ApplicationEvent;

public class AlertEvent extends ApplicationEvent {
    private Alert alert;

    public AlertEvent(Object source, Alert alert) {
        super(source);
        this.alert = alert;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }
}
