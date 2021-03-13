package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.data.HubLocationRepository;
import com.thomasdedinsky.fydp.fydpweb.data.WristbandLocationRepository;
import com.thomasdedinsky.fydp.fydpweb.data.WristbandRepository;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.HubLocation;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.models.WristbandLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final HubLocationRepository hubLocationRepository;
    private final WristbandLocationRepository wristbandLocationRepository;

    public LocationService(HubLocationRepository hubLocationRepository, WristbandLocationRepository wristbandLocationRepository) {
        this.hubLocationRepository = hubLocationRepository;
        this.wristbandLocationRepository = wristbandLocationRepository;
    }

    public Page<WristbandLocation> getAllWristbandLocations(Pageable pageable) {
        return wristbandLocationRepository.findAll(pageable);
    }
    public Page<WristbandLocation> getWristbandLocationsByWristband(Wristband wristband, Pageable pageable) {
        return wristbandLocationRepository.findByWristbandOrderByTimeStampDesc(wristband, pageable);
    }
    public long countWristbandLocations() {
        return wristbandLocationRepository.count();
    }
    public long countWristbandLocationsByWristband(Wristband wristband) {
        return wristbandLocationRepository.countByWristband(wristband);
    }

    public Page<HubLocation> getAllHubLocations(Pageable pageable) {
        return hubLocationRepository.findAll(pageable);
    }
    public Page<HubLocation> getHubLocationsByHub(Hub hub, Pageable pageable) {
        return hubLocationRepository.findByHubOrderByTimeStampDesc(hub, pageable);
    }
    public long countHubLocations() {
        return hubLocationRepository.count();
    }
    public long countHubLocationsByHub(Hub hub) {
        return hubLocationRepository.countByHub(hub);
    }

}
