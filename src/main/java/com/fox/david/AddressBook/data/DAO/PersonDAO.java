package com.fox.david.AddressBook.data.DAO;

import com.fox.david.AddressBook.data.Persistance.Person;
import com.fox.david.AddressBook.data.repositories.PersonRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
