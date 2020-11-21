package com.fox.david.AddressBook.userInterface;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class CommandLineInterface implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do{
            System.out.println("Please enter a command");
            input = scanner.next();
        }while(!input.equals("Q"));
    }
}
