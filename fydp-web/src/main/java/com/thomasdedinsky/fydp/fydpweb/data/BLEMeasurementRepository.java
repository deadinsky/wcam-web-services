package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.BLEMeasurement;
import com.thomasdedinsky.fydp.fydpweb.models.Hub;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BLEMeasurementRepository extends JpaRepository<BLEMeasurement, Integer> {
    List<BLEMeasurement> findByWristband(Wristband wristband);
    Page<BLEMeasurement> findByWristband(Wristband wristband, Pageable pageable);
    List<BLEMeasurement> findByHub(Hub hub);
    Page<BLEMeasurement> findByHub(Hub hub, Pageable pageable);
}
