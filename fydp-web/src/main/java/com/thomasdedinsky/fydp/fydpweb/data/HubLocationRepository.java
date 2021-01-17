package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.HubLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HubLocationRepository extends JpaRepository<HubLocation, Integer> {
    List<HubLocation> findByHubId(int hubId);
    Page<HubLocation> findByHubId(int hubId, Pageable pageable);
}
