package com.thomasdedinsky.fydp.fydpweb.models;

import com.thomasdedinsky.fydp.fydpweb.auth.User;

import javax.persistence.*;

@Entity
@Table(name="HUBS")
public class Hub {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="TX_POWER")
    private double txPower;
    @Column(name="SIGNAL_PROPAGATION_CONSTANT")
    private double signalPropagationConstant;

    public Hub() {
        super();
    }

    public Hub(int id, double txPower, double signalPropagationConstant) {
        super();
        this.id = id;
        this.txPower = txPower;
        this.signalPropagationConstant = signalPropagationConstant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
