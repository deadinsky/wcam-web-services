package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="BLE_MEASUREMENTS")
public class BLEMeasurement {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="WRISTBAND_ID")
    private int wristbandId;
    @Column(name="HUB_ID")
    private int hubId;
    @Column(name="STRENGTH")
    private double strength;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;

    public BLEMeasurement() {
        super();
    }

    public BLEMeasurement(int id, int wristbandId, int hubId, double strength, Timestamp timeStamp) {
        super();
        this.id = id;
        this.wristbandId = wristbandId;
        this.hubId = hubId;
        this.strength = strength;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWristbandId() {
        return wristbandId;
    }

    public void setWristbandId(int wristbandId) {
        this.wristbandId = wristbandId;
    }

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
