package com.thomasdedinsky.fydp.fydpweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PHONES")
public class Phone {
    @Id
    @Column(name="ID")
    private String id;

    public Phone() {
        super();
    }

    public Phone(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
