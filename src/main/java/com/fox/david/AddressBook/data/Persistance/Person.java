package com.fox.david.AddressBook.data.Persistance;

import com.fox.david.AddressBook.data.Persistance.Address;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue()
    private Long id;
    private String firstName;
    private String lastName;
    private List<Address> addresses;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
