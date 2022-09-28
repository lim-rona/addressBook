package com.example.addressBookServer.controllers;

import java.util.List;

import com.example.addressBookServer.models.Contact;
import com.example.addressBookServer.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
public class ContactRESTController {

    @Autowired
    private ContactService contactSvc;

    @PostMapping(path="/contacts",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postContact(@RequestBody String payload){

        Contact contact = new Contact();
        
        //Read the payload 
        try{
            contact = Contact.create(payload);
            //Verify I'm able to get the contact properly
            System.out.println("From contactController: " +contact.getName() +contact.getEmail()+contact.getMobile());
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception occured when reading payload");
        }
        //Save to database
        contactSvc.addContact(contact);

        //Return current list
        List<Contact> contactsList = contactSvc.retrieveAllContacts();
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for(Contact contacts: contactsList){
            System.out.println("contact's name is: " + contacts.getName());
            arr.add(contacts.toJson());
        }
        
        return ResponseEntity.ok(arr.build().toString());
    }

    @GetMapping(path="/contacts", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getContacts(){

        //Return current list
        List<Contact> contactsList = contactSvc.retrieveAllContacts();
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for(Contact contacts: contactsList){
            System.out.println("contact's name is: " + contacts.getName());
            arr.add(contacts.toJson());
        }
        
        return ResponseEntity.ok(arr.build().toString());

    }
    
}
