package com.fox.david.AddressBook.userInterface;

import com.fox.david.AddressBook.data.DAO.AddressDAO;
import com.fox.david.AddressBook.data.DAO.PersonDAO;
import com.fox.david.AddressBook.data.Persistance.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineInterface implements CommandLineRunner {

    PersonDAO personDAO;
    AddressDAO addressDAO;
    private static final String SPACE = " ";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";

    public CommandLineInterface(PersonDAO personDAO, AddressDAO addressDAO) {
        this.personDAO = personDAO;
        this.addressDAO = addressDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            System.out.println("Please enter a command");
            input = scanner.nextLine();
            System.out.println(parse(input));
        } while (!input.equals("Q"));
    }

    String parse(String input) {
        String[] commandAndArguements = input.split("\\s+");
        String response = "";
        if (commandAndArguements.length > 0) {
            String command = commandAndArguements[0];
            switch (command.toLowerCase()) {
                case "addperson":
                    String[] arguements = commandAndArguements[1].split(",");
                    response += personDAO.addPerson(arguements[0], arguements[1]);
                    break;
                case "list":
                    List<Person> people = personDAO.list();
                    StringBuilder builder = new StringBuilder();
                    for (Person person : people) {
                        builder.append(person.getFirstName()).append(SPACE).append(person.getLastName()).append(OPEN_BRACKET).append(person.getId()).append(CLOSE_BRACKET).append(System.lineSeparator());
                    }
                    response = builder.toString().trim();
                    break;
                case "count":
                    response = "Count:" + personDAO.count();
                    break;
                default:
                    return "could not parse given request";
            }
        }
        return response;
    }
}
