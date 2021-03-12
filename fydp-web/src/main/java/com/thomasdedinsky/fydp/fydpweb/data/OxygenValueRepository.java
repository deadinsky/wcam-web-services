package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.OxygenValue;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OxygenValueRepository extends JpaRepository<OxygenValue, Long> {
    List<OxygenValue> findByWristband(Wristband wristband);
    Page<OxygenValue> findByWristband(Wristband wristband, Pageable pageable);
    long countByWristband(Wristband wristband);
}
