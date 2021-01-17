package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
import com.thomasdedinsky.fydp.fydpweb.data.WristbandRepository;
import com.thomasdedinsky.fydp.fydpweb.models.Wristband;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WristbandService {
    private final WristbandRepository wristbandRepository;

    public WristbandService(WristbandRepository wristbandRepository) {
        this.wristbandRepository = wristbandRepository;
    }

    public List<Wristband> getAllWristbands() {
        return wristbandRepository.findAll();
    }
    public List<Wristband> getWristbandsByUser(User user) {
        return wristbandRepository.findByUser(user);
    }
}
