package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;

@Entity
@Table(name="WRISTBANDS")
public class Wristband {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="USER_ID")
    private int userId;

    public Wristband() {
        super();
    }

    public Wristband(int id, int userId) {
        super();
        this.id = id;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
