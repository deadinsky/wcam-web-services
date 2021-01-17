package com.thomasdedinsky.fydp.fydpweb.models;

import com.thomasdedinsky.fydp.fydpweb.auth.User;

import javax.persistence.*;

@Entity
@Table(name="WRISTBANDS")
public class Wristband {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="USER_ID", referencedColumnName="ID")
    private User user;

    public Wristband() {
        super();
    }

    public Wristband(int id, User user) {
        super();
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
