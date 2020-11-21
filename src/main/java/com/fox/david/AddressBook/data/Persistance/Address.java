package com.fox.david.AddressBook.data.Persistance;

import javax.persistence.*;

@Entity
public class Address {

    @Id()
    @GeneratedValue()
    String id;
    String street;
    String city;
    String state;
    String postalCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    Person owner;

    public Address(String street, String city, String state, String postalCode, Person owner) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.owner = owner;
    }
}
