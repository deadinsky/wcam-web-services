package com.thomasdedinsky.fydp.fydpweb;

import com.thomasdedinsky.fydp.fydpweb.models.Alert;
import com.thomasdedinsky.fydp.fydpweb.models.AlertEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AlertListener implements ApplicationListener<AlertEvent> {
    @Override
    public void onApplicationEvent(AlertEvent alertEvent) {
        System.out.println("Received spring custom event - " + alertEvent.getAlert().getId());
    }
}
