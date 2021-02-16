package com.thomasdedinsky.fydp.fydpweb.models;

import com.thomasdedinsky.fydp.fydpweb.Utilities;
import com.thomasdedinsky.fydp.fydpweb.auth.User;

import javax.persistence.*;

@Entity
@Table(name="WRISTBANDS")
public class Wristband {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String macAddress;
    @OneToOne
    @JoinColumn(name="USER_ID", referencedColumnName="ID")
    private User user;

    public Wristband() {
        super();
    }

    public Wristband(long id, User user) {
        super();
        this.id = id;
        this.user = user;
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

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
