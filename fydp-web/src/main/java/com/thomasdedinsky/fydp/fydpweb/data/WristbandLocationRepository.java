package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.models.DetailedIntermediate;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import com.thomasdedinsky.fydp.fydpweb.models.WristbandLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WristbandLocationRepository extends JpaRepository<WristbandLocation, Long> {
    Page<WristbandLocation> findByWristbandOrderByTimeStampDesc(Wristband wristband, Pageable pageable);
    Page<WristbandLocation> findByWristbandInOrderByTimeStampDesc(List<Wristband> wristbandList, Pageable pageable);
    List<WristbandLocation> findByIdIn(List<Long> idList);
    @Query(value = "SELECT " +
            "new com.thomasdedinsky.fydp.fydpweb.models.DetailedIntermediate(MAX(w.id), w.wristband) " +
            "FROM WristbandLocation w GROUP BY w.wristband")
    List<DetailedIntermediate> findAllDetailedWristbandsIntermediate();
    @Query(value = "SELECT " +
            "new com.thomasdedinsky.fydp.fydpweb.models.DetailedIntermediate(MAX(w.id), w.wristband) " +
            "FROM WristbandLocation w WHERE w.wristband.user = :user GROUP BY w.wristband")
    List<DetailedIntermediate> findAllDetailedWristbandsByUserIntermediate(@Param("user") User user);
    WristbandLocation findFirstByWristbandOrderByTimeStampDesc(Wristband wristband);
    long countByWristband(Wristband wristband);
    long countByWristbandIn(List<Wristband> wristbandList);
}
