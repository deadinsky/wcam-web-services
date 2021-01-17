package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.WristbandLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WristbandLocationRepository extends JpaRepository<WristbandLocation, Integer> {
}
