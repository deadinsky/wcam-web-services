package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.OxygenValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OxygenValueRepository extends JpaRepository<OxygenValue, Integer> {
    List<OxygenValue> findByWristbandId(int wristbandId);
    Page<OxygenValue> findByWristbandId(int wristbandId, Pageable pageable);
}
