package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.BLEMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BLEMeasurementRepository extends JpaRepository<BLEMeasurement, Integer> {
}
