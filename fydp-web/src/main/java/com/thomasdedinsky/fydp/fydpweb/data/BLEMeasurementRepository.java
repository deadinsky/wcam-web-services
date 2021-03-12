package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.BLEMeasurement;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BLEMeasurementRepository extends JpaRepository<BLEMeasurement, Long> {
    Page<BLEMeasurement> findByWristband(Wristband wristband, Pageable pageable);
    Page<BLEMeasurement> findByHub(Hub hub, Pageable pageable);
}
