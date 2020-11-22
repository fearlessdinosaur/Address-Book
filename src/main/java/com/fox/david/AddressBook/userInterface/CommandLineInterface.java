package com.fox.david.AddressBook.userInterface;

import com.fox.david.AddressBook.data.DAO.AddressDAO;
import com.fox.david.AddressBook.data.DAO.PersonDAO;
import com.fox.david.AddressBook.data.Persistance.Address;
import com.fox.david.AddressBook.data.Persistance.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineInterface {

    PersonDAO personDAO;
    AddressDAO addressDAO;
    private static final String SPACE = " ";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String SEPARATOR = ",";

    public CommandLineInterface(PersonDAO personDAO, AddressDAO addressDAO) {
        this.personDAO = personDAO;
        this.addressDAO = addressDAO;
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            System.out.println("Please enter a command");
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
            System.out.println(parse(input));
        } while (!input.equals("Q"));
        System.exit(0);
    }

    String parse(String input) {
        String[] commandAndArguements = input.split("\\s+");
        String response = "";
        String[] arguements;

        if (commandAndArguements.length > 0) {
            String command = commandAndArguements[0];
            switch (command.toLowerCase()) {
                case "addperson":
                    arguements = input.substring(commandAndArguements[0].length()).split(",");
                    response += personDAO.addPerson(arguements[0], arguements[1]);
                    break;
                case "deleteperson":
                    response = personDAO.delete(Long.parseLong(commandAndArguements[1].trim()));
                    break;
                case "deleteaddress":
                    response = addressDAO.delete(Long.parseLong(commandAndArguements[1].trim()));
                    break;
                case "addaddress":
                    arguements = input.substring(commandAndArguements[0].length()).split(",");
                    response = personDAO.addAddress(Long.parseLong(arguements[0].trim()), arguements[1], arguements[2], arguements[3], arguements[4]);
                    break;
                case "changeaddress":
                    arguements = input.substring(commandAndArguements[0].length()).split(",");
                    response = addressDAO.updateAddress(Long.parseLong(arguements[0].trim()), arguements[1], arguements[2], arguements[3], arguements[4]);
                case "changeperson":
                    arguements = input.substring(commandAndArguements[0].length()).split(",");
                    response = personDAO.updatePerson(Long.parseLong(arguements[0].trim()), arguements[1], arguements[2]);
                case "list":
                    List<Person> people = personDAO.list();
                    StringBuilder builder = new StringBuilder();
                    for (Person person : people) {
                        builder.append(person.getFirstName()).append(SPACE).append(person.getLastName()).append(OPEN_BRACKET).append(person.getId()).append(CLOSE_BRACKET).append(System.lineSeparator());
                        if (!person.getAddresses().isEmpty()) {
                            for (Address address : person.getAddresses()) {
                                builder.append("\t")
                                        .append(address.getStreet())
                                        .append(SEPARATOR)
                                        .append(address.getCity())
                                        .append(SEPARATOR)
                                        .append(address.getState())
                                        .append(SEPARATOR)
                                        .append(address.getPostalCode())
                                        .append(System.lineSeparator());
                            }
                        }
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
