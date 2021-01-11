package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ECG_VALUES")
public class ECGValue {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="WRISTBAND_ID")
    private int wristbandId;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;
    @Column(name="VALUE")
    private double value;

    public ECGValue() {
        super();
    }

    public ECGValue(int id, int wristbandId, Timestamp timeStamp, double value) {
        super();
        this.id = id;
        this.wristbandId = wristbandId;
        this.timeStamp = timeStamp;
        this.value = value;
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

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}