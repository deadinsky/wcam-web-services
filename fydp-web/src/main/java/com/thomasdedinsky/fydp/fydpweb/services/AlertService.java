package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.data.AlertRepository;
import com.thomasdedinsky.fydp.fydpweb.models.Alert;
import com.thomasdedinsky.fydp.fydpweb.models.AlertEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {
    @Autowired
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AlertRepository alertRepository;

    public AlertService(ApplicationEventPublisher applicationEventPublisher, AlertRepository alertRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.alertRepository = alertRepository;
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public void modifyAlert(Alert alert) {
        alertRepository.save(alert);
        if (alert.isSevere()) {
            applicationEventPublisher.publishEvent(new AlertEvent(this, alert));
        }
    }

    public void refreshAlert(String message) {
        Optional<Alert> alert = alertRepository.findById(message);
        if (alert.isPresent() && alert.get().isSevere()) {
            applicationEventPublisher.publishEvent(new AlertEvent(this, alert.get()));
        }
    }

    @EventListener(ContextRefreshedEvent.class)
    public void initializeAlerts() {
        Utilities.initializeAlerts(alertRepository.findAll());
    }
}
