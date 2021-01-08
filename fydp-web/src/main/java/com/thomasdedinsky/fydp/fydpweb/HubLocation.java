package com.thomasdedinsky.fydp.fydpweb;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="HUB_LOCATIONS")
public class HubLocation {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;
    @Column(name="HUB_ID")
    private int hubId;
    @Column(name="LOCATION_X")
    private double locationX;
    @Column(name="LOCATION_Y")
    private double locationY;
    @Column(name="LOCATION_Z")
    private double locationZ;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;

    public HubLocation() {
        super();
    }

    public HubLocation(int id, int hubId, double locationX, double locationY, double locationZ, Timestamp timeStamp) {
        super();
        this.id = id;
        this.hubId = hubId;
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

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
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
