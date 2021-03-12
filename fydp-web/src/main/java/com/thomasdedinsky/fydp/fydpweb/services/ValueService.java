package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.data.*;
import com.thomasdedinsky.fydp.fydpweb.models.*;
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
    public List<ECGValue> getECGValuesByWristband(Wristband wristband) {
        return ecgValueRepository.findByWristband(wristband);
    }
    public Page<ECGValue> getECGValuesByWristband(Wristband wristband, Pageable pageable) {
        return ecgValueRepository.findByWristband(wristband, pageable);
    }
    public long countECGValues() {
        return ecgValueRepository.count();
    }
    public long countECGValuesByWristband(Wristband wristband) {
        return ecgValueRepository.countByWristband(wristband);
    }

    public List<HeartRateValue> getAllHeartRateValues() {
        return heartRateValueRepository.findAll();
    }
    public Page<HeartRateValue> getAllHeartRateValues(Pageable pageable) {
        return heartRateValueRepository.findAll(pageable);
    }
    public List<HeartRateValue> getHeartRateValuesByWristband(Wristband wristband) {
        return heartRateValueRepository.findByWristband(wristband);
    }
    public Page<HeartRateValue> getHeartRateValuesByWristband(Wristband wristband, Pageable pageable) {
        return heartRateValueRepository.findByWristband(wristband, pageable);
    }
    public long countHeartRateValues() {
        return heartRateValueRepository.count();
    }
    public long countHeartRateValuesByWristband(Wristband wristband) {
        return heartRateValueRepository.countByWristband(wristband);
    }

    public List<OxygenValue> getAllOxygenValues() {
        return oxygenValueRepository.findAll();
    }
    public Page<OxygenValue> getAllOxygenValues(Pageable pageable) {
        return oxygenValueRepository.findAll(pageable);
    }
    public List<OxygenValue> getOxygenValuesByWristband(Wristband wristband) {
        return oxygenValueRepository.findByWristband(wristband);
    }
    public Page<OxygenValue> getOxygenValuesByWristband(Wristband wristband, Pageable pageable) {
        return oxygenValueRepository.findByWristband(wristband, pageable);
    }
    public long countOxygenValues() {
        return oxygenValueRepository.count();
    }
    public long countOxygenValuesByWristband(Wristband wristband) {
        return oxygenValueRepository.countByWristband(wristband);
    }

    public List<SkinTempValue> getAllSkinTempValues() {
        return skinTempValueRepository.findAll();
    }
    public Page<SkinTempValue> getAllSkinTempValues(Pageable pageable) {
        return skinTempValueRepository.findAll(pageable);
    }
    public List<SkinTempValue> getSkinTempValuesByWristband(Wristband wristband) {
        return skinTempValueRepository.findByWristband(wristband);
    }
    public Page<SkinTempValue> getSkinTempValuesByWristband(Wristband wristband, Pageable pageable) {
        return skinTempValueRepository.findByWristband(wristband, pageable);
    }
    public long countSkinTempValues() {
        return skinTempValueRepository.count();
    }
    public long countSkinTempValuesByWristband(Wristband wristband) {
        return skinTempValueRepository.countByWristband(wristband);
    }
}
