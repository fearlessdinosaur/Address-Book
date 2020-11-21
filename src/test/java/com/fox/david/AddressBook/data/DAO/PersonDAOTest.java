package com.fox.david.AddressBook.data.DAO;

import com.fox.david.AddressBook.data.Persistance.Person;
import com.fox.david.AddressBook.data.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PersonDAOTest {
    @Mock
    PersonRepository repository;

    @InjectMocks
    PersonDAO personDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCount() {
        long num = 1;
        Mockito.doReturn(num).when(repository).count();

        String result = personDAO.count();

        Assertions.assertEquals("1", result);
    }

    @Test
    void addPerson_FailureCase() {
        Mockito.doThrow(NullPointerException.class).when(repository).save(Mockito.any());

        String result = personDAO.addPerson("name", "lastname");
        Assertions.assertEquals("Failed to add person", result);
    }

    @Test
    void deletePerson_FailureCase() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        String result = personDAO.delete(Long.parseLong("2"));

        Assertions.assertEquals("failed to find person with ID 2", result);
    }

    @Test
    void testList() {
        List<Person> people = Collections.singletonList(new Person("james", "smith"));

        Mockito.doReturn(people).when(repository).findAll();

        List<Person> result = personDAO.list();

        Assertions.assertFalse(result.isEmpty());
    }
}
