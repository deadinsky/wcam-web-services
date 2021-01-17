package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.BLEMeasurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BLEMeasurementRepository extends JpaRepository<BLEMeasurement, Integer> {
    List<BLEMeasurement> findByWristbandId(int wristbandId);
    Page<BLEMeasurement> findByWristbandId(int wristbandId, Pageable pageable);
    List<BLEMeasurement> findByHubId(int hubId);
    Page<BLEMeasurement> findByHubId(int hubId, Pageable pageable);
}
