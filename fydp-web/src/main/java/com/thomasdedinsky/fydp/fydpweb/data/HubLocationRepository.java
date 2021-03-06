package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.DetailedIntermediate;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.HubLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HubLocationRepository extends JpaRepository<HubLocation, Long> {
    Page<HubLocation> findByHubOrderByTimeStampDesc(Hub hub, Pageable pageable);
    Page<HubLocation> findByHubInOrderByTimeStampDesc(List<Hub> hubList, Pageable pageable);
    List<HubLocation> findByIdIn(List<Long> idList);
    HubLocation findFirstByHubOrderByTimeStampDesc(Hub hub);
    @Query(value = "SELECT " +
            "new com.thomasdedinsky.fydp.fydpweb.models.DetailedIntermediate(MAX(h.id), h.hub) " +
            "FROM HubLocation h GROUP BY h.hub")
    List<DetailedIntermediate> findAllDetailedHubsIntermediate();
    long countByHub(Hub hub);
    long countByHubIn(List<Hub> hubList);
}
