package com.thomasdedinsky.fydp.fydpweb.services;

import com.thomasdedinsky.fydp.fydpweb.auth.User;
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

    public Page<ECGValue> getAllECGValues(Pageable pageable) {
        return ecgValueRepository.findAllByOrderByTimeStampDesc(pageable);
    }
    public Page<ECGValue> getECGValuesByWristband(Wristband wristband, Pageable pageable) {
        return ecgValueRepository.findByWristbandOrderByTimeStampDesc(wristband, pageable);
    }
    public Page<ECGValue> getECGValuesByWristbandIn(List<Wristband> wristbandList, Pageable pageable) {
        return ecgValueRepository.findByWristbandInOrderByTimeStampDesc(wristbandList, pageable);
    }
    public long countECGValues() {
        return ecgValueRepository.count();
    }
    public long countECGValuesByWristband(Wristband wristband) {
        return ecgValueRepository.countByWristband(wristband);
    }
    public long countECGValuesByWristbandIn(List<Wristband> wristbandList) {
        return ecgValueRepository.countByWristbandIn(wristbandList);
    }

    public Page<HeartRateValue> getAllHeartRateValues(Pageable pageable) {
        return heartRateValueRepository.findAllByOrderByTimeStampDesc(pageable);
    }
    public Page<HeartRateValue> getHeartRateValuesByWristband(Wristband wristband, Pageable pageable) {
        return heartRateValueRepository.findByWristbandOrderByTimeStampDesc(wristband, pageable);
    }
    public Page<HeartRateValue> getHeartRateValuesByWristbandIn(List<Wristband> wristbandList, Pageable pageable) {
        return heartRateValueRepository.findByWristbandInOrderByTimeStampDesc(wristbandList, pageable);
    }
    public long countHeartRateValues() {
        return heartRateValueRepository.count();
    }
    public long countHeartRateValuesByWristband(Wristband wristband) {
        return heartRateValueRepository.countByWristband(wristband);
    }
    public long countHeartRateValuesByWristbandIn(List<Wristband> wristbandList) {
        return heartRateValueRepository.countByWristbandIn(wristbandList);
    }

    public Page<OxygenValue> getAllOxygenValues(Pageable pageable) {
        return oxygenValueRepository.findAllByOrderByTimeStampDesc(pageable);
    }
    public Page<OxygenValue> getOxygenValuesByWristband(Wristband wristband, Pageable pageable) {
        return oxygenValueRepository.findByWristbandOrderByTimeStampDesc(wristband, pageable);
    }
    public Page<OxygenValue> getOxygenValuesByWristbandIn(List<Wristband> wristbandList, Pageable pageable) {
        return oxygenValueRepository.findByWristbandInOrderByTimeStampDesc(wristbandList, pageable);
    }
    public long countOxygenValues() {
        return oxygenValueRepository.count();
    }
    public long countOxygenValuesByWristband(Wristband wristband) {
        return oxygenValueRepository.countByWristband(wristband);
    }
    public long countOxygenValuesByWristbandIn(List<Wristband> wristbandList) {
        return oxygenValueRepository.countByWristbandIn(wristbandList);
    }

    public Page<SkinTempValue> getAllSkinTempValues(Pageable pageable) {
        return skinTempValueRepository.findAllByOrderByTimeStampDesc(pageable);
    }
    public Page<SkinTempValue> getSkinTempValuesByWristband(Wristband wristband, Pageable pageable) {
        return skinTempValueRepository.findByWristbandOrderByTimeStampDesc(wristband, pageable);
    }
    public Page<SkinTempValue> getSkinTempValuesByWristbandIn(List<Wristband> wristbandList, Pageable pageable) {
        return skinTempValueRepository.findByWristbandInOrderByTimeStampDesc(wristbandList, pageable);
    }
    public long countSkinTempValues() {
        return skinTempValueRepository.count();
    }
    public long countSkinTempValuesByWristband(Wristband wristband) {
        return skinTempValueRepository.countByWristband(wristband);
    }
    public long countSkinTempValuesByWristbandIn(List<Wristband> wristbandList) {
        return skinTempValueRepository.countByWristbandIn(wristbandList);
    }
}
