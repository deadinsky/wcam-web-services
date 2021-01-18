package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.services.LocationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;
    private final int PAGE_SIZE_LIMIT = 100;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String getLocations(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal,
                               @RequestParam(name = "type") String type,
                               @RequestParam(name = "pageSize") int pageSize,
                               @RequestParam(name = "pageNum") int pageNum,
                               @RequestParam(name = "hub", required = false) Hub hub,
                               @RequestParam(name = "wristband", required = false) Wristband wristband) {
        if ((pageSize < 1 || pageSize >= PAGE_SIZE_LIMIT) || pageNum < 0) {
            return "locations";
        }
        if ((hub != null && hub.getId() < 0) || (wristband != null && wristband.getId() < 0)) {
            return "locations";
        }
        switch (type) {
            case "hub":
                if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
                    if (hub == null) {
                        model.addAttribute("hubLocations", locationService.getAllHubLocations(PageRequest.of(pageNum, pageSize)));
                    } else {
                        model.addAttribute("hubLocations", locationService.getHubLocationsByHub(hub, PageRequest.of(pageNum, pageSize)));
                    }
                }
                break;
            case "wristband":
                if (userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
                    if (hub == null) {
                        model.addAttribute("wristbandLocations", locationService.getAllWristbandLocations(PageRequest.of(pageNum, pageSize)));
                    } else {
                        model.addAttribute("wristbandLocations", locationService.getWristbandLocationsByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                    }
                } else if (hub != null) {
                    model.addAttribute("wristbandLocations", locationService.getWristbandLocationsByUser(userPrincipal.getUser(), PageRequest.of(pageNum, pageSize)));
                }
                break;
            default:
                break;
        }
        return "locations";
    }
}
