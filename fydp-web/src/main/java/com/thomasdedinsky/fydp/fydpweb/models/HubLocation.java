package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="HUB_LOCATIONS")
public class HubLocation {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="HUB_ID", referencedColumnName="ID")
    private Hub hub;
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

    public HubLocation(Hub hub) {
        super();
        this.id = 0;
        this.hub = hub;
        this.locationX = 0;
        this.locationY = 0;
        this.locationZ = 0;
        this.timeStamp = new Timestamp(0);
    }

    public HubLocation(int id, Hub hub, double locationX, double locationY, double locationZ, Timestamp timeStamp) {
        super();
        this.id = id;
        this.hub = hub;
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

    public Hub getHub() {
        return hub;
    }

    public int getHubId() {
        return hub.getId();
    }

    public void setHub(Hub hub) {
        this.hub = hub;
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
