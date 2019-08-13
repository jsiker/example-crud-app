package com.handshake.example.crud.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "alarms")
public class Alarm implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private BigInteger id;

    @Column(name = "text", length= 300)
    private String text;

    public Alarm() {
    }

    public Alarm(String text) {
        this.text = text;
    }

    public Alarm(BigInteger id, String text) {
        this.id = id;
        this.text = text;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String listItemEntry() {
        return id + " -- " + text;
    }
}
