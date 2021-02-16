package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="HEART_RATE_VALUES")
public class HeartRateValue {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name="WRISTBAND_ID", referencedColumnName="ID")
    private Wristband wristband;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;
    @Column(name="VALUE")
    private double value;
    @Column(name="CONFIDENCE")
    private double confidence;

    public HeartRateValue() {
        super();
    }

    public HeartRateValue(long id, Wristband wristband, Timestamp timeStamp, double value, double confidence) {
        super();
        this.id = id;
        this.wristband = wristband;
        this.timeStamp = timeStamp;
        this.value = value;
        this.confidence = confidence;
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

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
