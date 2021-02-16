package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="BLE_MEASUREMENTS")
public class BLEMeasurement {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name="WRISTBAND_ID", referencedColumnName="ID")
    private Wristband wristband;
    @OneToOne
    @JoinColumn(name="HUB_ID", referencedColumnName="ID")
    private Hub hub;
    @Column(name="STRENGTH")
    private double strength;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;

    public BLEMeasurement() {
        super();
    }

    public BLEMeasurement(long id, Wristband wristband, Hub hub, double strength, Timestamp timeStamp) {
        super();
        this.id = id;
        this.wristband = wristband;
        this.hub = hub;
        this.strength = strength;
        this.timeStamp = timeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wristband getWristband() {
        return wristband;
    }

    public void setWristband(Wristband wristband) {
        this.wristband = wristband;
    }

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
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
