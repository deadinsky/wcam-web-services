package com.thomasdedinsky.fydp.fydpweb;

import javax.persistence.*;

@Entity
@Table(name="HUBS")
public class Hub {
    @Id
    @Column(name="ID")
    @GeneratedValue
    private int id;
}
