package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="WRISTBAND_LOCATIONS")
public class WristbandLocation {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="WRISTBAND_ID", referencedColumnName="ID")
    private Wristband wristband;
    @Column(name="LOCATION_X")
    private double locationX;
    @Column(name="LOCATION_Y")
    private double locationY;
    @Column(name="LOCATION_Z")
    private double locationZ;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;

    public WristbandLocation() {
        super();
    }

    public WristbandLocation(Wristband wristband) {
        super();
        this.id = 0;
        this.wristband = wristband;
        this.locationX = 0;
        this.locationY = 0;
        this.locationZ = 0;
        this.timeStamp = new Timestamp(0);
    }

    public WristbandLocation(int id, Wristband wristband, double locationX, double locationY, double locationZ, Timestamp timeStamp) {
        super();
        this.id = id;
        this.wristband = wristband;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.timeStamp = timeStamp;
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

    public int getWristbandId() {
        return wristband.getId();
    }

    public void setWristband(Wristband wristband) {
        this.wristband = wristband;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public double getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(double locationZ) {
        this.locationZ = locationZ;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
