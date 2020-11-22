package com.fox.david.AddressBook.data.Persistance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue()
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setOwner(this);
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {

    }


}
