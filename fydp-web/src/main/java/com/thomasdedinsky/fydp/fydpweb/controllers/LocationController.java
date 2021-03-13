package com.thomasdedinsky.fydp.fydpweb.controllers;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.auth.UserPrincipal;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.services.HubService;
import com.thomasdedinsky.fydp.fydpweb.services.LocationService;
import com.thomasdedinsky.fydp.fydpweb.services.WristbandService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private final HubService hubService;
    private final LocationService locationService;
    private final WristbandService wristbandService;
    private final static int PAGE_SIZE_LIMIT = 100;

    public LocationController(HubService hubService, LocationService locationService, WristbandService wristbandService) {
        this.hubService = hubService;
        this.locationService = locationService;
        this.wristbandService = wristbandService;
    }

    public static String redirectWithParams(String type, int pageSize, int pageNum, Hub hub, String hName,
                                            Wristband wristband, String wName, boolean willRedirect) {
        if (type == null || (!type.equals("hub") && !type.equals("wristband"))) {
            willRedirect = true;
            type = (hub != null ? "hub" : "wristband");
        }
        if (pageNum < 0) {
            willRedirect = true;
            pageNum = 0;
        }
        if (pageSize < 1) {
            willRedirect = true;
            pageSize = 50;
        }
        if (pageSize > PAGE_SIZE_LIMIT) {
            willRedirect = true;
            pageSize = PAGE_SIZE_LIMIT;
        }
        String redirectString = String.format("redirect:/locations?type=%s&pageNum=%d&pageSize=%d",
                type, pageNum, pageSize);
        if (type.equals("hub")) {
            if (wristband != null || (wName != null && !wName.isEmpty())) {
                willRedirect = true;
            }
            if (hub != null) {
                if (hub.getId() < 0) {
                    willRedirect = true;
                } else {
                    redirectString += "&hub=" + hub.getId();
                }
            }
            if (hName != null && !hName.isEmpty()) {
                redirectString += "&hName=" + hName;
            }
        } else if (type.equals("wristband")) {
            if (hub != null || (hName != null && !hName.isEmpty())) {
                willRedirect = true;
            }
            if (wristband != null) {
                if (wristband.getId() < 0) {
                    willRedirect = true;
                } else {
                    redirectString += "&wristband=" + wristband.getId();
                }
            }
            if (wName != null && !wName.isEmpty()) {
                redirectString += "&wName=" + wName;
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
                               @RequestParam(name = "hName", required = false) String hName,
                               @RequestParam(name = "wristband", required = false) Wristband wristband,
                               @RequestParam(name = "wName", required = false) String wName) {
        boolean willRedirect = false;
        List<Hub> hubList = new ArrayList<>();
        List<Wristband> wristbandList = new ArrayList<>();
        if (!userPrincipal.getAuthorities().contains(userPrincipal.authorityAdmin)) {
            return "redirect:/";
        } else if (type != null && type.equals("hub")) {
            if (hub != null && hName != null && !hName.isEmpty()) {
                hName = null;
                willRedirect = true;
            } else if (hName != null && !hName.isEmpty()) {
                hubList = hubService.getHubsByNickname(hName);
                if (hubList.size() == 1) {
                    hub = hubList.get(0);
                    hName = null;
                    willRedirect = true;
                }
            }
        } else if (type != null && type.equals("wristband")) {
            if (wristband != null && wName != null && !wName.isEmpty()) {
                wName = null;
                willRedirect = true;
            } else if (wName != null && !wName.isEmpty()) {
                wristbandList = wristbandService.getWristbandsByNickname(wName);
                if (wristbandList.size() == 1) {
                    wristband = wristbandList.get(0);
                    wName = null;
                    willRedirect = true;
                }
            }
        }
        String redirectString = redirectWithParams(type, pageSize, pageNum, hub, hName, wristband, wName, willRedirect);
        if (!redirectString.equals("")) {
            return redirectString;
        }
        Utilities.addModelAttributes(model, userPrincipal.getUser());
        model.addAttribute("type", type);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("hub", (hub != null ? hub.getId() : ""));
        model.addAttribute("hName", (hName != null ? hName : ""));
        model.addAttribute("wristband", (wristband != null ? wristband.getId() : ""));
        model.addAttribute("wName", (wName != null ? wName : ""));
        long total = 0;
        switch (type) {
            case "hub":
                model.addAttribute("title", "Hub Locations");
                if (hName != null && !hName.isEmpty()) {
                    model.addAttribute("hubLocations", locationService.getHubLocationsByHubIn(hubList, PageRequest.of(pageNum, pageSize)));
                    total = locationService.countHubLocationsByHubIn(hubList);
                } else if (hub == null) {
                    model.addAttribute("hubLocations", locationService.getAllHubLocations(PageRequest.of(pageNum, pageSize)));
                    total = locationService.countHubLocations();
                } else {
                    model.addAttribute("hubLocations", locationService.getHubLocationsByHub(hub, PageRequest.of(pageNum, pageSize)));
                    total = locationService.countHubLocationsByHub(hub);
                }
                break;
            case "wristband":
                model.addAttribute("title", "Wristband Locations");
                if (wName != null && !wName.isEmpty()) {
                    model.addAttribute("wristbandLocations", locationService.getWristbandLocationsByWristbandIn(wristbandList, PageRequest.of(pageNum, pageSize)));
                    total = locationService.countWristbandLocationsByWristbandIn(wristbandList);
                } else if (wristband == null) {
                    model.addAttribute("wristbandLocations", locationService.getAllWristbandLocations(PageRequest.of(pageNum, pageSize)));
                    total = locationService.countWristbandLocations();
                } else {
                    model.addAttribute("wristbandLocations", locationService.getWristbandLocationsByWristband(wristband, PageRequest.of(pageNum, pageSize)));
                    total = locationService.countWristbandLocationsByWristband(wristband);
                }
                break;
            default:
                break;
        }
        model.addAttribute("firstCount", Math.min(total, pageSize * pageNum));
        model.addAttribute("lastCount", Math.min(total, pageSize * (pageNum + 1)));
        model.addAttribute("total", total);
        return "locations";
    }
}
