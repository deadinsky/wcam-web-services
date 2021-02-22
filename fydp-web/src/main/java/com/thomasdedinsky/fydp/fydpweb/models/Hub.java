package com.thomasdedinsky.fydp.fydpweb.models;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.auth.User;

import javax.persistence.*;

@Entity
@Table(name="HUBS")
public class Hub {
    @Id
    @Column(name="ID")
    private long id;
    private String macAddress;
    @Column(name="TX_POWER")
    private double txPower;
    @Column(name="SIGNAL_PROPAGATION_CONSTANT")
    private double signalPropagationConstant;

    public Hub() {
        super();
    }

    public Hub(long id, double txPower, double signalPropagationConstant) {
        super();
        this.id = id;
        this.txPower = txPower;
        this.signalPropagationConstant = signalPropagationConstant;
    }

    public long getId() {
        return id;
    }

    public String getMacAddress() {
        if (macAddress == null) {
            macAddress = Utilities.longToMacAddress(id);
        }
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        this.id = Utilities.macAddressToLong(macAddress);
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTxPower() {
        return txPower;
    }

    public void setTxPower(double txPower) {
        this.txPower = txPower;
    }

    public double getSignalPropagationConstant() {
        return signalPropagationConstant;
    }

    public void setSignalPropagationConstant(double signalPropagationConstant) {
        this.signalPropagationConstant = signalPropagationConstant;
    }
}
