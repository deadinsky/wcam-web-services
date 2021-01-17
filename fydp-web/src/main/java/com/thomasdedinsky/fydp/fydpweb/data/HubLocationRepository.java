package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.HubLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HubLocationRepository extends JpaRepository<HubLocation, Integer> {
    List<HubLocation> findByHub(Hub hub);
    Page<HubLocation> findByHub(Hub hub, Pageable pageable);
}
