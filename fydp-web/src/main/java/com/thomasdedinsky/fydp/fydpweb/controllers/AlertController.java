package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.models.Alert;
import com.thomasdedinsky.fydp.fydpweb.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/alerts")
public class AlertController {
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public String getAllAlerts(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin) &&
                !userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            return "redirect:/";
        }
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        List<Alert> alerts = alertService.getAllAlerts();
        model.addAttribute("alerts", alerts);
        alertService.updateAlert(alerts.get(0));
        return "alerts";
    }

    @PostMapping
    public void updateAlert(Alert alert) {
        alertService.updateAlert(alert);
    }
}
