package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.auth.UserService;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.services.WristbandService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wristbands")
public class WristbandController {
    private final WristbandService wristbandService;
    private final UserService userService;

    public WristbandController(WristbandService wristbandService, UserService userService) {
        this.wristbandService = wristbandService;
        this.userService = userService;
    }

    @GetMapping
    public String getWristbands(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin) ||
                userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            model.addAttribute("detailedWristbands", wristbandService.getAllDetailedWristbands());
        } else {
            model.addAttribute("detailedWristbands", wristbandService.getDetailedWristbandsByUser(userPrincipal.getUser()));
        }
        return "wristbands";
    }

    @RequestMapping("/add")
    public String addWristband(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("isAdmin", userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin));
        return "wristbands-add";
    }

    @PostMapping("/add")
    public String addWristband(Wristband wristband, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            wristband.setUser(userPrincipal.getUser());
        }
        wristbandService.addWristband(wristband);
        return "redirect:/wristbands";
    }

    @RequestMapping("/modify")
    public String modifyWristband(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            return "redirect:/wristbands";
        }
        model.addAttribute("detailedWristbands", wristbandService.getDetailedWristbandsByUser(userService.getZeroUser()));
        return "wristbands-modify";
    }

    @PostMapping("/modify")
    public String modifyWristband(Wristband wristband, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            return "redirect:/wristbands";
        }
        wristbandService.addWristband(wristband);
        return "redirect:/wristbands/modify";
    }
}
