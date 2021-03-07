package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.auth.UserService;
import com.thomasdedinsky.fydp.fydpweb.services.WristbandService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profiles")
public class ProfileController {
    private final UserService userService;
    private final WristbandService wristbandService;

    public ProfileController(UserService userService, WristbandService wristbandService) {
        this.userService = userService;
        this.wristbandService = wristbandService;
    }

    @GetMapping
    public String getAllProfiles(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            return "redirect:/profiles/" + userPrincipal.getUser().getId();
        }
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        model.addAttribute("users", userService.getAllUsers());
        return "users-all";
    }

    @RequestMapping("/{user}")
    public String getProfileByUser(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable User user) {
        if (!(userPrincipal.getUser().getId() == user.getId()) && !userPrincipal.getAuthorities().contains(userPrincipal.authorityManager)) {
            return "redirect:/profiles/" + userPrincipal.getUser().getId();
        }
        if (user == null) {
            return "redirect:/profiles";
        }
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        model.addAttribute("user", user);
        model.addAttribute("detailedWristbands", wristbandService.getDetailedWristbandsByUser(user));
        return "users-one";
    }
}
