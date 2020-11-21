package com.fox.david.AddressBook.data.DAO;

import com.fox.david.AddressBook.data.Persistance.Address;
import com.fox.david.AddressBook.data.Persistance.Person;
import com.fox.david.AddressBook.data.repositories.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    PersonRepository personRepository;

    public PersonDAO(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public String addPerson(String firstName, String lastName) {
        try {
            Person person = new Person(firstName, lastName);
            personRepository.save(person);
            return "added person " + firstName + " " + lastName;
        } catch (Exception e) {
            return "Failed to add person";
        }
    }

    public List<Person> list() {
        return (List<Person>) personRepository.findAll();
    }

    public String count() {
        return String.valueOf(personRepository.count());
    }

    public String delete(long id) {
        if (personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
            return "Person Successfully deleted";
        } else {
            return "failed to find person with ID " + id;
        }
    }

    public String addAddress(long id, String street, String city, String state, String postalCode) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Address address = new Address(street, city, state, postalCode);
            Person personEntity = person.get();
            personEntity.addAddress(address);
            personRepository.save(personEntity);
            return "successfully added address";
        }
        return "could not find person";
    }

    public String updatePerson(Long id, String firstName, String lastName) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Person personEntity = person.get();
            if (firstName != null) {
                personEntity.setFirstName(firstName);
            }
            if (lastName != null) {
                personEntity.setLastName(lastName);
            }
            personRepository.save(personEntity);
            return "successfully updated person";
        }
        return "could not find person to update";
    }
}
