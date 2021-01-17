package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.SkinTempValue;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinTempValueRepository extends JpaRepository<SkinTempValue, Integer> {
    List<SkinTempValue> findByWristband(Wristband wristband);
    Page<SkinTempValue> findByWristband(Wristband wristband, Pageable pageable);
}
