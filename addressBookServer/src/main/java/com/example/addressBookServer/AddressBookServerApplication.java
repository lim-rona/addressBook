package com.example.addressBookServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class AddressBookServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressBookServerApplication.class, args);
	}

}
