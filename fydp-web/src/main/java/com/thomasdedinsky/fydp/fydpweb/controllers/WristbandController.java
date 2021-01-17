package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.services.WristbandService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wristbands")
public class WristbandController {
    private final WristbandService wristbandService;

    public WristbandController(WristbandService wristbandService) {
        this.wristbandService = wristbandService;
    }

    @GetMapping
    public String getWristbands(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal.getAuthorities().contains(userPrincipal.authorityManager) ||
                userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            model.addAttribute("wristbands", wristbandService.getAllWristbands());
        } else {
            model.addAttribute("wristbands", wristbandService.getWristbandsByUser(userPrincipal.getUser()));
        }
        return "wristbands";
    }
}
