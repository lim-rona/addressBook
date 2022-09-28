package com.example.addressBookServer.service;

import java.util.List;

import com.example.addressBookServer.models.Contact;
import com.example.addressBookServer.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepo;
    
    public boolean addContact(Contact contact){
        int updated = contactRepo.insertContact(contact);
        return updated==1;
    }

    public List<Contact> retrieveAllContacts(){
        List<Contact> contactsList = contactRepo.getAllContacts();
        return contactsList;
    }
}
