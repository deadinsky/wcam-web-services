package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="WRISTBAND_LOCATIONS")
public class WristbandLocation {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    @Column(name="APPROXIMATE_DISTANCE_IN_METRES")
    private double approximateDistanceInMetres;

    public WristbandLocation() {
        super();
    }

    public WristbandLocation(Wristband wristband) {
        super();
        this.id = 0;
        this.wristband = wristband;
    }

    public WristbandLocation(int id, Wristband wristband, double locationX, double locationY, double locationZ, Timestamp timeStamp, double approximateDistanceInMetres) {
        super();
        this.id = id;
        this.wristband = wristband;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.timeStamp = timeStamp;
        this.approximateDistanceInMetres = approximateDistanceInMetres;
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

    public long getWristbandId() {
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

    public double getApproximateDistanceInMetres() {
        return approximateDistanceInMetres;
    }

    public void setApproximateDistanceInMetres(double approximateDistanceInMetres) {
        this.approximateDistanceInMetres = approximateDistanceInMetres;
    }
}
