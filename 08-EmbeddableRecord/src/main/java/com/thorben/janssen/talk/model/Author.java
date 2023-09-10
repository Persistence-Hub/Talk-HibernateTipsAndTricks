package com.thorben.janssen.talk.model;

import org.hibernate.annotations.EmbeddableInstantiator;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;

@Entity
public class Author {

    @EmbeddedId
    private AuthorId id;

    @Version
    private int version;

    private String firstName;

    private String lastName;

    @Embedded
    private Address address;

    public AuthorId getId() {
        return id;
    }

    public void setId(AuthorId id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Author [id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName
                + ", address=" + address + "]";
    }
}
