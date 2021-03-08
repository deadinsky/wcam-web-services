package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.services.ValueService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/values")
public class ValueController {
    private final ValueService valueService;
    private final static int PAGE_SIZE_LIMIT = 100;

    public ValueController(ValueService valueService) {
        this.valueService = valueService;
    }

    public static String redirectWithParams(String type, int pageSize, int pageNum, Wristband wristband) {
        boolean willRedirect = false;
        if (type == null || (!type.equals("ecg") && !type.equals("heartrate") &&
                !type.equals("oxygen") && !type.equals("skintemp"))) {
            willRedirect = true;
            type = "ecg";
        }
        if (pageSize < 1) {
            willRedirect = true;
            pageSize = 1;
        }
        if (pageSize > PAGE_SIZE_LIMIT) {
            willRedirect = true;
            pageSize = PAGE_SIZE_LIMIT;
        }
        if (pageNum < 0) {
            willRedirect = true;
            pageNum = 0;
        }
        String redirectString = String.format("redirect:/values?type=%s&pageSize=%d&pageNum=%d",
                type, pageSize, pageNum);
        if (wristband != null) {
            if (wristband.getId() < 0) {
                willRedirect = true;
            } else {
                redirectString += "&wristband=" + wristband.getId();
            }
        }
        return (willRedirect ? redirectString : "");
    }

    @GetMapping
    public String getValues(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal,
                            @RequestParam(name = "type", required = false) String type,
                            @RequestParam(name = "pageSize", defaultValue = "-1") int pageSize,
                            @RequestParam(name = "pageNum", defaultValue = "-1") int pageNum,
                            @RequestParam(name = "wristband", required = false) Wristband wristband) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin) && (wristband == null ||
                !wristband.getUser().equals(userPrincipal.getUser()))) {
            return "redirect:/";
        }
        String redirectString = redirectWithParams(type, pageSize, pageNum, wristband);
        if (!redirectString.equals("")) {
            return redirectString;
        }
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        if (wristband == null) {
            switch (type) {
                case "ecg":
                    model.addAttribute("title", "ECG Values");
                    model.addAttribute("values", valueService.getAllECGValues(PageRequest.of(pageNum, pageSize)));
                    break;
                case "heartrate":
                    model.addAttribute("title", "Heart Rate Values");
                    model.addAttribute("values", valueService.getAllHeartRateValues(PageRequest.of(pageNum, pageSize)));
                    break;
                case "oxygen":
                    model.addAttribute("title", "SpO2 Values");
                    model.addAttribute("values", valueService.getAllOxygenValues(PageRequest.of(pageNum, pageSize)));
                    break;
                case "skintemp":
                    model.addAttribute("title", "Skin Temp. Values");
                    model.addAttribute("values", valueService.getAllSkinTempValues(PageRequest.of(pageNum, pageSize)));
                    break;
                default:
                    break;
            }
            return "values";
        }
        switch (type) {
            case "ecg":
                model.addAttribute("title", "ECG Values");
                model.addAttribute("values", valueService.getECGValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            case "heartrate":
                model.addAttribute("title", "Heart Rate Values");
                model.addAttribute("values", valueService.getHeartRateValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            case "oxygen":
                model.addAttribute("title", "SpO2 Values");
                model.addAttribute("values", valueService.getOxygenValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            case "skintemp":
                model.addAttribute("title", "Skin Temp. Values");
                model.addAttribute("values", valueService.getSkinTempValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            default:
                break;
        }
        return "values";
    }
}
