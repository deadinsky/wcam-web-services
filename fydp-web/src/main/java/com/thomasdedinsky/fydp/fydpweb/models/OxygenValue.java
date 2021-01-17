package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="OXYGEN_VALUES")
public class OxygenValue {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="WRISTBAND_ID", referencedColumnName="ID")
    private Wristband wristband;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;
    @Column(name="VALUE")
    private double value;

    public OxygenValue() {
        super();
    }

    public OxygenValue(int id, Wristband wristband, Timestamp timeStamp, double value) {
        super();
        this.id = id;
        this.wristband = wristband;
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
