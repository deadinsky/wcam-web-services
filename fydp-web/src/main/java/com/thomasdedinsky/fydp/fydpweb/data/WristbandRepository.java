package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WristbandRepository extends JpaRepository<Wristband, Integer> {
}
