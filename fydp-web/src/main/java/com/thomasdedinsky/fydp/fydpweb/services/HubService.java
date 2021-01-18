package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.data.HubLocationRepository;
import com.thomasdedinsky.fydp.fydpweb.data.HubRepository;
import com.thomasdedinsky.fydp.fydpweb.models.DetailedIntermediate;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.HubLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HubService {
    private final HubRepository hubRepository;
    private final HubLocationRepository hubLocationRepository;

    public HubService(HubRepository hubRepository, HubLocationRepository hubLocationRepository) {
        this.hubRepository = hubRepository;
        this.hubLocationRepository = hubLocationRepository;
    }

    public List<Hub> getAllHubs() {
        return hubRepository.findAll();
    }

    public Page<Hub> getAllHubs(Pageable pageable) {
        return hubRepository.findAll(pageable);
    }

    public List<HubLocation> getAllDetailedHubs() {
        List<DetailedIntermediate> detailedIntermediateList =
                hubLocationRepository.findAllDetailedHubsIntermediate();
        if (detailedIntermediateList.size() == 0) {
            return hubRepository.findAll().stream().map(h -> new HubLocation(h)).collect(Collectors.toList());
        }
        List<Integer> maxIds = detailedIntermediateList
                .stream().map(di -> di.getMaxId()).collect(Collectors.toList());
        List<HubLocation> hubsWithLocation = Collections.synchronizedList(hubLocationRepository.findByIdIn(maxIds));
        List<Integer> hubIdsToExclude = hubsWithLocation
                .stream().map(hwl -> hwl.getHub().getId()).collect(Collectors.toList());
        List<Hub> hubsWithoutLocation = hubRepository.findByIdNotIn(hubIdsToExclude);
        hubsWithLocation.addAll(
                hubsWithoutLocation.stream().map(hwl -> new HubLocation(hwl)).collect(Collectors.toList()));
        return hubsWithLocation
                .stream().sorted(Comparator.comparingInt(HubLocation::getHubId)).collect(Collectors.toList());
    }
}
