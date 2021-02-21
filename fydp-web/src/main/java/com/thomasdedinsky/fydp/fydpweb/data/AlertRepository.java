package com.thomasdedinsky.fydp.fydpweb.data;

import com.thomasdedinsky.fydp.fydpweb.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, String> {
    List<Alert> findAll();
}
