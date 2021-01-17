package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.SkinTempValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinTempValueRepository extends JpaRepository<SkinTempValue, Integer> {
    List<SkinTempValue> findByWristbandId(int wristbandId);
    Page<SkinTempValue> findByWristbandId(int wristbandId, Pageable pageable);
}
