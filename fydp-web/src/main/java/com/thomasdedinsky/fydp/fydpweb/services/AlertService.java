package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.data.AlertRepository;
import com.thomasdedinsky.fydp.fydpweb.data.PhoneRepository;
import com.thomasdedinsky.fydp.fydpweb.models.Alert;
import com.thomasdedinsky.fydp.fydpweb.models.AlertEvent;
import com.thomasdedinsky.fydp.fydpweb.models.Phone;
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
    private final PhoneRepository phoneRepository;

    public AlertService(ApplicationEventPublisher applicationEventPublisher, AlertRepository alertRepository, PhoneRepository phoneRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.alertRepository = alertRepository;
        this.phoneRepository = phoneRepository;
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findByIsActive(true);
    }

    public void modifyAlert(Alert alert) {
        alertRepository.save(alert);
        if (alert.isSevere()) {
            applicationEventPublisher.publishEvent(new AlertEvent(this, alert));
        }
    }

    public void refreshAlert(String message) {
        Alert alert = alertRepository.findFirstByMessageOrderByTimeStampDesc(message);
        if (alert != null && alert.isSevere()) {
            applicationEventPublisher.publishEvent(new AlertEvent(this, alert));
        }
    }

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public void addPhone(Phone phone) {
        phoneRepository.save(phone);
        Utilities.addPhone(phone);
    }

    public void removePhone(Phone phone) {
        phoneRepository.delete(phone);
        Utilities.removePhone(phone);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void initializeAlerts() {
        Utilities.initializeAlerts(alertRepository.findByIsActive(true));
        Utilities.initializePhones(phoneRepository.findAll());
    }
}
