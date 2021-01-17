package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.WristbandLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WristbandLocationRepository extends JpaRepository<WristbandLocation, Integer> {
    List<WristbandLocation> findByWristbandId(int wristbandId);
    Page<WristbandLocation> findByWristbandId(int wristbandId, Pageable pageable);
}
