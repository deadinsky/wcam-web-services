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
    private final int PAGE_SIZE_LIMIT = 100;

    public ValueController(ValueService valueService) {
        this.valueService = valueService;
    }

    @GetMapping
    public String getValues(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal,
                            @RequestParam(name = "type") String type,
                            @RequestParam(name = "pageSize") int pageSize,
                            @RequestParam(name = "pageNum") int pageNum,
                            @RequestParam(name = "wristband", required = false) Wristband wristband) {
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        if ((pageSize < 1 || pageSize >= PAGE_SIZE_LIMIT) || pageNum < 0 || (wristband != null && wristband.getId() < 0)) {
            return "values";
        }
        if (wristband == null && !userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            return "values";
        }
        if (wristband == null) {
            switch (type) {
                case "ecg":
                    model.addAttribute("values", valueService.getAllECGValues(PageRequest.of(pageNum, pageSize)));
                    break;
                case "heartrate":
                    model.addAttribute("values", valueService.getAllHeartRateValues(PageRequest.of(pageNum, pageSize)));
                    break;
                case "oxygen":
                    model.addAttribute("values", valueService.getAllOxygenValues(PageRequest.of(pageNum, pageSize)));
                    break;
                case "skintemp":
                    model.addAttribute("values", valueService.getAllSkinTempValues(PageRequest.of(pageNum, pageSize)));
                    break;
                default:
                    break;
            }
            return "values";
        }
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin) &&
                !wristband.getUser().equals(userPrincipal.getUser())) {
            return "values";
        }
        switch (type) {
            case "ecg":
                model.addAttribute("values", valueService.getECGValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            case "heartrate":
                model.addAttribute("values", valueService.getHeartRateValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            case "oxygen":
                model.addAttribute("values", valueService.getOxygenValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            case "skintemp":
                model.addAttribute("values", valueService.getSkinTempValuesByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                break;
            default:
                break;
        }
        return "values";
    }
}
