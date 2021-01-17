package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.data.*;
import com.thomasdedinsky.fydp.fydpweb.models.ECGValue;
import com.thomasdedinsky.fydp.fydpweb.models.HeartRateValue;
import com.thomasdedinsky.fydp.fydpweb.models.OxygenValue;
import com.thomasdedinsky.fydp.fydpweb.models.SkinTempValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValueService {
    private final ECGValueRepository ecgValueRepository;
    private final HeartRateValueRepository heartRateValueRepository;
    private final OxygenValueRepository oxygenValueRepository;
    private final SkinTempValueRepository skinTempValueRepository;

    public ValueService(ECGValueRepository ecgValueRepository, HeartRateValueRepository heartRateValueRepository,
                        OxygenValueRepository oxygenValueRepository, SkinTempValueRepository skinTempValueRepository) {
        this.ecgValueRepository = ecgValueRepository;
        this.heartRateValueRepository = heartRateValueRepository;
        this.oxygenValueRepository = oxygenValueRepository;
        this.skinTempValueRepository = skinTempValueRepository;
    }

    public List<ECGValue> getAllECGValues() {
        return ecgValueRepository.findAll();
    }
    public Page<ECGValue> getAllECGValues(Pageable pageable) {
        return ecgValueRepository.findAll(pageable);
    }
    public List<ECGValue> getECGValuesByWristbandId(int wristbandId) {
        return ecgValueRepository.findByWristbandId(wristbandId);
    }
    public Page<ECGValue> getECGValuesByWristbandId(int wristbandId, Pageable pageable) {
        return ecgValueRepository.findByWristbandId(wristbandId, pageable);
    }

    public List<HeartRateValue> getAllHeartRateValues() {
        return heartRateValueRepository.findAll();
    }
    public Page<HeartRateValue> getAllHeartRateValues(Pageable pageable) {
        return heartRateValueRepository.findAll(pageable);
    }
    public List<HeartRateValue> getHeartRateValuesByWristbandId(int wristbandId) {
        return heartRateValueRepository.findByWristbandId(wristbandId);
    }
    public Page<HeartRateValue> getHeartRateValuesByWristbandId(int wristbandId, Pageable pageable) {
        return heartRateValueRepository.findByWristbandId(wristbandId, pageable);
    }

    public List<OxygenValue> getAllOxygenValues() {
        return oxygenValueRepository.findAll();
    }
    public Page<OxygenValue> getAllOxygenValues(Pageable pageable) {
        return oxygenValueRepository.findAll(pageable);
    }
    public List<OxygenValue> getOxygenValuesByWristbandId(int wristbandId) {
        return oxygenValueRepository.findByWristbandId(wristbandId);
    }
    public Page<OxygenValue> getOxygenValuesByWristbandId(int wristbandId, Pageable pageable) {
        return oxygenValueRepository.findByWristbandId(wristbandId, pageable);
    }

    public List<SkinTempValue> getAllSkinTempValues() {
        return skinTempValueRepository.findAll();
    }
    public Page<SkinTempValue> getAllSkinTempValues(Pageable pageable) {
        return skinTempValueRepository.findAll(pageable);
    }
    public List<SkinTempValue> getSkinTempValuesByWristbandId(int wristbandId) {
        return skinTempValueRepository.findByWristbandId(wristbandId);
    }
    public Page<SkinTempValue> getSkinTempValuesByWristbandId(int wristbandId, Pageable pageable) {
        return skinTempValueRepository.findByWristbandId(wristbandId, pageable);
    }
}
