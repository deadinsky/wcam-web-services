package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.data.AlertRepository;
import com.thomasdedinsky.fydp.fydpweb.models.Alert;
import com.thomasdedinsky.fydp.fydpweb.models.AlertEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    private final AlertRepository alertRepository;

    public AlertService(ApplicationEventPublisher applicationEventPublisher, AlertRepository alertRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.alertRepository = alertRepository;
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public void updateAlert(Alert alert) {
        applicationEventPublisher.publishEvent(new AlertEvent(this, alert));
    }
}
