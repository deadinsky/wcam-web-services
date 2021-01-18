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

    public Hub() {
        super();
    }

    public Hub(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
