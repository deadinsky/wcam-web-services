package com.thomasdedinsky.fydp.fydpweb.models;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.auth.User;

import javax.persistence.*;

@Entity
@Table(name="WRISTBANDS")
public class Wristband {
    @Id
    @Column(name="ID")
    private long id;
    private String macAddress;
    @Column(name="NICKNAME")
    private String nickname;
    @OneToOne
    @JoinColumn(name="USER_ID", referencedColumnName="ID")
    private User user;

    public Wristband() {
        super();
    }

    public Wristband(long id, String nickname, User user) {
        super();
        this.id = id;
        this.nickname = nickname;
        this.user = user;
    }

    public Wristband(long id, User user) {
        super();
        this.id = id;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
