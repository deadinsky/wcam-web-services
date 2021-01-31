package com.thomasdedinsky.fydp.fydpweb.controllers;


import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.HubLocation;
import com.thomasdedinsky.fydp.fydpweb.services.HubService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;
import java.sql.Timestamp;

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

    @RequestMapping("/add")
    public String addHub(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            return "hubs-add";
        }
        return "redirect:/hubs";
    }

    @PostMapping("/add")
    public String addHub(Hub hub, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            hubService.addHub(hub);
        }
        return "redirect:/hubs/modify/" + String.valueOf(hub.getId());
    }

    @RequestMapping("/modify/{hub}")
    public String modifyHub(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Hub hub) {
        if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin) && hub != null) {
            model.addAttribute("detailedHub", hubService.getDetailedHub(hub));
            return "hubs-modify";
        }
        return "redirect:/hubs";
    }

    @PostMapping("/modify/{hub}")
    public String modifyHub(HubLocation hubLocation, @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Hub hub) {
        if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            hubLocation.setHub(hub);
            hubLocation.setTimeStamp(new Timestamp(System.currentTimeMillis()));
            hubService.addHubLocation(hubLocation);
        }
        return "redirect:/hubs";
    }
}