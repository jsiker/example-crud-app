package com.noyo.example.crud.models;

import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable{

    @EmbeddedId
    private UserIdVersionCompositeKey key;

    @Column(insertable = false, updatable = false)
    private Integer id;

    @Column(name = "firstname", length = 50)
    private String firstName;

    @Column(name = "middlename", length = 50)
    private String middleName;

    @Column(name = "lastname", length = 50)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "age")
    private Integer age;

    @Column(name = "numofversions")
    private Integer numOfVersions;

    public User() {
    }

    public User(UserIdVersionCompositeKey key) {
        this.key = key;
    }

    private User(Builder builder) {
        this.key = builder.key;
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.age = builder.age;
        this.numOfVersions = builder.numOfVersions;
    }

    public static Builder newUser() {
        return new Builder();
    }

    public UserIdVersionCompositeKey getKey() {
        return key;
    }

    public void setKey(UserIdVersionCompositeKey key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumOfVersions() {
        return numOfVersions;
    }

    public void setNumOfVersions(Integer numOfVersions) {
        this.numOfVersions = numOfVersions;
    }


    public static final class Builder {
        private UserIdVersionCompositeKey key;
        private Integer id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String email;
        private Integer age;
        private Integer numOfVersions;

        private Builder() {
        }

        public User build() {
            return new User(this);
        }

        public Builder key(UserIdVersionCompositeKey key) {
            this.key = key;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder numOfVersions(Integer numOfVersions) {
            this.numOfVersions = numOfVersions;
            return this;
        }
    }
}
