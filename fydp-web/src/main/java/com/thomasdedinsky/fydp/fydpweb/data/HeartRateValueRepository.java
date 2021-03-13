package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.HeartRateValue;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRateValueRepository extends JpaRepository<HeartRateValue, Long> {
    Page<HeartRateValue> findAllByOrderByTimeStampDesc(Pageable pageable);
    Page<HeartRateValue> findByWristbandOrderByTimeStampDesc(Wristband wristband, Pageable pageable);
    Page<HeartRateValue> findByWristbandInOrderByTimeStampDesc(List<Wristband> wristbandList, Pageable pageable);
    long countByWristband(Wristband wristband);
    long countByWristbandIn(List<Wristband> wristbandList);
}
