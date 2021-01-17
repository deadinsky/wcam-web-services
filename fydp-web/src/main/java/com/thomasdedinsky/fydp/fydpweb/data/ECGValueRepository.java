package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.ECGValue;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ECGValueRepository extends JpaRepository<ECGValue, Integer> {
    List<ECGValue> findByWristband(Wristband wristband);
    Page<ECGValue> findByWristband(Wristband wristband, Pageable pageable);
}
