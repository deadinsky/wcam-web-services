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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
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
        model.addAttribute("alerts", alertService.getAllAlerts());
        return "alerts";
    }

    @GetMapping("/modify")
    public String modifyAlert(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin) &&
                !userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            return "redirect:/";
        }
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        return "alerts-modify";
    }

    @PostMapping("/modify")
    public String modifyAlert(Alert alert, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin) &&
                !userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            return "redirect:/";
        }
        alert.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        alertService.modifyAlert(alert);
        return "redirect:/alerts";
    }

    @GetMapping("/refresh/{message}")
    public String refreshAlert(Model model, @PathVariable String message) {
        alertService.refreshAlert(message);
        return "blank";
    }
}
