package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WristbandRepository extends JpaRepository<Wristband, Integer> {
    List<Wristband> findByUserId(int userId);
    Page<Wristband> findByUserId(int userId, Pageable pageable);
}
