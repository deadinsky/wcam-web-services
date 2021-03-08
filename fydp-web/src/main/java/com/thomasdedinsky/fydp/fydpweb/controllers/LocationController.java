package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
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
    private final static int PAGE_SIZE_LIMIT = 100;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    public static String redirectWithParams(String type, int pageSize, int pageNum, Hub hub, Wristband wristband) {
        boolean willRedirect = false;
        if (type == null || (!type.equals("hub") && !type.equals("wristband"))) {
            willRedirect = true;
            type = (hub != null ? "hub" : "wristband");
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
        String redirectString = String.format("redirect:/locations?type=%s&pageSize=%d&pageNum=%d",
                type, pageSize, pageNum);
        if (type.equals("hub")) {
            if (wristband != null) {
                willRedirect = true;
            }
            if (hub != null) {
                if (hub.getId() < 0) {
                    willRedirect = true;
                } else {
                    redirectString += "&hub=" + hub.getId();
                }
            }
        } else if (type.equals("wristband")) {
            if (hub != null) {
                willRedirect = true;
            }
            if (wristband != null) {
                if (wristband.getId() < 0) {
                    willRedirect = true;
                } else {
                    redirectString += "&wristband=" + wristband.getId();
                }
            }
        }
        return (willRedirect ? redirectString : "");
    }

    @GetMapping
    public String getLocations(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal,
                               @RequestParam(name = "type", required = false) String type,
                               @RequestParam(name = "pageSize", defaultValue = "-1") int pageSize,
                               @RequestParam(name = "pageNum", defaultValue = "-1") int pageNum,
                               @RequestParam(name = "hub", required = false) Hub hub,
                               @RequestParam(name = "wristband", required = false) Wristband wristband) {
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            return "redirect:/";
        }
        String redirectString = redirectWithParams(type, pageSize, pageNum, hub, wristband);
        if (!redirectString.equals("")) {
            return redirectString;
        }
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        switch (type) {
            case "hub":
                model.addAttribute("title", "Hub Locations");
                if (hub == null) {
                    model.addAttribute("hubLocations", locationService.getAllHubLocations(PageRequest.of(pageNum, pageSize)));
                } else {
                    model.addAttribute("hubLocations", locationService.getHubLocationsByHub(hub, PageRequest.of(pageNum, pageSize)));
                }
                break;
            case "wristband":
                model.addAttribute("title", "Wristband Locations");
                if (wristband == null) {
                    model.addAttribute("wristbandLocations", locationService.getAllWristbandLocations(PageRequest.of(pageNum, pageSize)));
                } else {
                    model.addAttribute("wristbandLocations", locationService.getWristbandLocationsByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                }
                break;
            default:
                break;
        }
        return "locations";
    }
}
