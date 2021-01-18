package com.thomasdedinsky.fydp.fydpweb.controllers;


import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.services.HubService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hubs")
public class HubController {
    private final HubService hubService;

    public HubController(HubService hubService) {
        this.hubService = hubService;
    }

    @GetMapping
    public String getHubs(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            model.addAttribute("detailedHubs", hubService.getAllDetailedHubs());
        }
        return "hubs";
    }
}