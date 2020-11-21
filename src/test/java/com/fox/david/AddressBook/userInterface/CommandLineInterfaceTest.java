package com.fox.david.AddressBook.userInterface;

import com.fox.david.AddressBook.data.DAO.PersonDAO;
import com.fox.david.AddressBook.data.Persistance.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class CommandLineInterfaceTest {

    @Mock
    PersonDAO personDAO;

    private final String ADD_PERSON = "addPerson James,Smith";
    private final String ERROR_MESSAGE = "could not parse given request";
    private ArrayList<Person> people;
    private String LIST_OUTPUT = "david fox(null)";

    @InjectMocks
    CommandLineInterface cli;

    @BeforeEach()
    void init() {
        MockitoAnnotations.openMocks(this);
        people = new ArrayList<>();
        people.add(new Person("david", "fox"));
    }

    @Test
    void testList() {
        Mockito.doReturn(people).when(personDAO).list();

        String result = cli.parse("list").trim();

        Assertions.assertEquals(LIST_OUTPUT, result);
    }

    @Test
    void TestCount() {
        Mockito.doReturn(String.valueOf(people.size())).when(personDAO).count();

        String result = cli.parse("count");

        Assertions.assertEquals("Count:1", result);
    }

    @Test
    void testInvalidInput() {
        String result = cli.parse("nonExistentRequest");
        Assertions.assertEquals(ERROR_MESSAGE, result);
    }
}
