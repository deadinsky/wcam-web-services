package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.services.WristbandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wristbands")
public class WristbandRestController {
    private final WristbandService wristbandService;

    public WristbandRestController(WristbandService wristbandService) {
        this.wristbandService = wristbandService;
    }

    @GetMapping
    public List<Wristband> getAllWristbands() {
        return wristbandService.getAllWristbands();
    }
}
