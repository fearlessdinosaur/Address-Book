package com.fox.david.AddressBook;

import com.fox.david.AddressBook.userInterface.CommandLineInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class AddressBookApplication implements CommandLineRunner {

	CommandLineInterface commandLineInterface;

	public AddressBookApplication(CommandLineInterface commandLineInterface){
		this.commandLineInterface = commandLineInterface;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(AddressBookApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		commandLineInterface.run();
	}
}
