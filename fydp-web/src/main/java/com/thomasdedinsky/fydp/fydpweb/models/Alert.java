package com.thomasdedinsky.fydp.fydpweb.models;

import org.springframework.context.ApplicationEvent;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="ALERTS")
public class Alert {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name="IS_SEVERE")
    private boolean isSevere;
    @Column(name="IS_ACTIVE")
    private boolean isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
