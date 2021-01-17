package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.ECGValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ECGValueRepository extends JpaRepository<ECGValue, Integer> {
}
