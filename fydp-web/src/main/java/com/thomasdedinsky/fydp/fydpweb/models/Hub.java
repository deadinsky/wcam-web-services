package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.*;

@Entity
@Table(name="HUBS")
public class Hub {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
