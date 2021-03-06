package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.ECGValue;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ECGValueRepository extends JpaRepository<ECGValue, Long> {
    Page<ECGValue> findAllByOrderByTimeStampDesc(Pageable pageable);
    Page<ECGValue> findByWristbandOrderByTimeStampDesc(Wristband wristband, Pageable pageable);
    Page<ECGValue> findByWristbandInOrderByTimeStampDesc(List<Wristband> wristbandList, Pageable pageable);
    long countByWristband(Wristband wristband);
    long countByWristbandIn(List<Wristband> wristbandList);
}
