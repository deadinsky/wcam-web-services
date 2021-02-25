package com.thomasdedinsky.fydp.fydpweb.models;

import org.springframework.context.ApplicationEvent;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ALERTS")
public class Alert {
    @Id
    @Column(name="ID")
    private long id;
    @OneToOne
    @JoinColumn(name="WRISTBAND_ID", referencedColumnName="ID")
    private Wristband wristband;
    @Column(name="MESSAGE")
    private String message;
    @Column(name="IS_SEVERE")
    private boolean isSevere;
    private String isSevereString;
    @Column(name="IS_ACTIVE")
    private boolean isActive;
    private String isActiveString;
    @Column(name="CONTACT_STATUS")
    private String contactStatus;
    @Column(name="TIME_STAMP")
    private Timestamp timeStamp;

    public Alert() {
        super();
    }

    public Alert(long id, Wristband wristband, String message, boolean isSevere, boolean isActive, String contactStatus, Timestamp timeStamp) {
        super();
        this.id = id;
        this.wristband = wristband;
        this.message = message;
        this.isSevere = isSevere;
        this.isActive = isActive;
        this.contactStatus = contactStatus;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
