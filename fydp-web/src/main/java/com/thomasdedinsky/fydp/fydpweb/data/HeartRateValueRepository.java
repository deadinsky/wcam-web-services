package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.HeartRateValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRateValueRepository extends JpaRepository<HeartRateValue, Integer> {
    List<HeartRateValue> findByWristbandId(int wristbandId);
    Page<HeartRateValue> findByWristbandId(int wristbandId, Pageable pageable);
}
