package com.fox.david.AddressBook.data.repositories;

import com.fox.david.AddressBook.data.Persistance.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
