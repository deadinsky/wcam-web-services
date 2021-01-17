package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.HeartRateValue;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRateValueRepository extends JpaRepository<HeartRateValue, Integer> {
    List<HeartRateValue> findByWristband(Wristband wristband);
    Page<HeartRateValue> findByWristband(Wristband wristband, Pageable pageable);
}
