package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.HeartRateValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRateValueRepository extends JpaRepository<HeartRateValue, Integer> {
}
