package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.services.WristbandService;
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
    public String getAllWristbands(Model model) {
        model.addAttribute("wristbands", wristbandService.getAllWristbands());
        return "wristbands";
    }
}
