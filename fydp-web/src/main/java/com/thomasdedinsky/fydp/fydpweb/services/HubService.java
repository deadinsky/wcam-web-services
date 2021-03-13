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

    public Hub addHub(Hub hub) {
        return hubRepository.save(hub);
    }

    public HubLocation addHubLocation(HubLocation hubLocation) {
        return hubLocationRepository.save(hubLocation);
    }

    public List<Hub> getHubsByNickname(String nickname) {
        return hubRepository.findByNickname(nickname);
    }

    public HubLocation getDetailedHub(Hub hub) {
        HubLocation hubLocation = hubLocationRepository.findFirstByHubOrderByTimeStampDesc(hub);
        if (hubLocation == null) {
            hubLocation = new HubLocation(hub);
        }
        return hubLocation;
    }

    public List<HubLocation> getAllDetailedHubs() {
        List<DetailedIntermediate> detailedIntermediateList =
                hubLocationRepository.findAllDetailedHubsIntermediate();
        if (detailedIntermediateList.size() == 0) {
            return hubRepository.findAll().stream().map(h -> new HubLocation(h)).collect(Collectors.toList());
        }
        List<Long> maxIds = detailedIntermediateList
                .stream().map(di -> di.getMaxId()).collect(Collectors.toList());
        List<HubLocation> hubsWithLocation = Collections.synchronizedList(hubLocationRepository.findByIdIn(maxIds));
        List<Long> hubIdsToExclude = hubsWithLocation
                .stream().map(hwl -> hwl.getHub().getId()).collect(Collectors.toList());
        List<Hub> hubsWithoutLocation = hubRepository.findByIdNotIn(hubIdsToExclude);
        hubsWithLocation.addAll(
                hubsWithoutLocation.stream().map(hwl -> new HubLocation(hwl)).collect(Collectors.toList()));
        return hubsWithLocation
                .stream().sorted(Comparator.comparingLong(HubLocation::getHubId)).collect(Collectors.toList());
    }
}
