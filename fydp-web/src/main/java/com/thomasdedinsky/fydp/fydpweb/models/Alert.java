package com.thomasdedinsky.fydp.fydpweb.models;

import org.springframework.context.ApplicationEvent;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ALERTS")
public class Alert {
    @Id
    @Column(name="ID")
    private String id;
    @Column(name="IS_SEVERE")
    private boolean isSevere;
    private String isSevereString;
    @Column(name="IS_ACTIVE")
    private boolean isActive;
    private String isActiveString;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;

    public Alert() {
        super();
    }

    public Alert(String id, boolean isSevere, boolean isActive, Timestamp timeStamp) {
        super();
        this.id = id;
        this.isSevere = isSevere;
        this.isActive = isActive;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSevere() {
        return isSevere;
    }

    public void setSevere(boolean severe) {
        isSevere = severe;
    }

    public void setIsSevereString(String isSevereString) {
        this.isSevereString = isSevereString;
        this.isSevere = "on".equals(isSevereString);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setIsActiveString(String isActiveString) {
        this.isActiveString = isActiveString;
        this.isActive = "on".equals(isActiveString);
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
