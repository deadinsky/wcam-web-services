package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.data.WristbandLocationRepository;
import com.thomasdedinsky.fydp.fydpweb.data.WristbandRepository;
import com.thomasdedinsky.fydp.fydpweb.models.DetailedIntermediate;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.models.WristbandLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WristbandService {
    private final WristbandRepository wristbandRepository;
    private final WristbandLocationRepository wristbandLocationRepository;

    public WristbandService(WristbandRepository wristbandRepository, WristbandLocationRepository wristbandLocationRepository) {
        this.wristbandRepository = wristbandRepository;
        this.wristbandLocationRepository = wristbandLocationRepository;
    }

    public Wristband addWristband(Wristband wristband) {
        return wristbandRepository.save(wristband);
    }

    public List<Wristband> getWristbandsByUser(User user) {
        return wristbandRepository.findByUser(user);
    }

    public List<WristbandLocation> getAllDetailedWristbands() {
        List<DetailedIntermediate> detailedIntermediateList =
                wristbandLocationRepository.findAllDetailedWristbandsIntermediate();
        if (detailedIntermediateList.size() == 0) {
            return wristbandRepository.findAll().stream().map(w -> new WristbandLocation(w)).collect(Collectors.toList());
        }
        List<Long> maxIds = detailedIntermediateList
                .stream().map(di -> di.getMaxId()).collect(Collectors.toList());
        List<WristbandLocation> wristbandsWithLocation = Collections.synchronizedList(wristbandLocationRepository.findByIdIn(maxIds));
        List<Long> wristbandIdsToExclude = wristbandsWithLocation
                .stream().map(wwl -> wwl.getWristband().getId()).collect(Collectors.toList());
        List<Wristband> wristbandsWithoutLocation = wristbandRepository.findByIdNotIn(wristbandIdsToExclude);
        wristbandsWithLocation.addAll(
                wristbandsWithoutLocation.stream().map(wwl -> new WristbandLocation(wwl)).collect(Collectors.toList()));
        return wristbandsWithLocation
                .stream().sorted(Comparator.comparingLong(WristbandLocation::getWristbandId)).collect(Collectors.toList());
    }

    public List<WristbandLocation> getDetailedWristbandsByUser(User user) {
        List<DetailedIntermediate> detailedIntermediateList =
                wristbandLocationRepository.findAllDetailedWristbandsByUserIntermediate(user);
        if (detailedIntermediateList.size() == 0) {
            return wristbandRepository.findByUser(user).stream().map(w -> new WristbandLocation(w)).collect(Collectors.toList());
        }
        List<Long> maxIds = detailedIntermediateList
                .stream().map(di -> di.getMaxId()).collect(Collectors.toList());
        List<WristbandLocation> wristbandsWithLocation = Collections.synchronizedList(wristbandLocationRepository.findByIdIn(maxIds));
        List<Long> wristbandIdsToExclude = wristbandsWithLocation
                .stream().map(wwl -> wwl.getWristband().getId()).collect(Collectors.toList());
        List<Wristband> wristbandsWithoutLocation = wristbandRepository.findByIdNotInAndUser(wristbandIdsToExclude, user);
        wristbandsWithLocation.addAll(
                wristbandsWithoutLocation.stream().map(wwl -> new WristbandLocation(wwl)).collect(Collectors.toList()));
        return wristbandsWithLocation
                .stream().sorted(Comparator.comparingLong(WristbandLocation::getWristbandId)).collect(Collectors.toList());
    }

    public WristbandLocation getDetailedWristband(Wristband wristband) {
        WristbandLocation detailedWristband = wristbandLocationRepository.findFirstByWristbandOrderByTimeStampDesc(wristband);
        if (detailedWristband != null) {
            return detailedWristband;
        }
        return new WristbandLocation(wristband);
    }
}
