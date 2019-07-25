package com.noyo.example.crud.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Embeddable
public class UserIdVersionCompositeKey implements Serializable{

    @Column(name = "id")
    private Integer id;

    @Nullable
    @Column(name = "version")
    private Integer version;

    public UserIdVersionCompositeKey() {
    }

    public UserIdVersionCompositeKey(Integer id) {
        this.id = id;
    }

    public UserIdVersionCompositeKey(Integer id, Integer version) {
        this.id = id;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
