package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.models.WristbandLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WristbandLocationRepository extends JpaRepository<WristbandLocation, Integer> {
    List<WristbandLocation> findByWristband(Wristband wristband);
    Page<WristbandLocation> findByWristband(Wristband wristband, Pageable pageable);
    List<WristbandLocation> findByWristbandIn(List<Wristband> wristbandList);
    Page<WristbandLocation> findByWristbandIn(List<Wristband> wristbandList, Pageable pageable);
}
