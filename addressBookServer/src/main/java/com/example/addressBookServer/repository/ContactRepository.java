package com.example.addressBookServer.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.addressBookServer.models.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

    private static final String SQL_INSERT_CONTACT="insert into user(name,email,mobile) values (?, ?, ?)";

    private static final String SQL_RETRIEVE_ALL_CONTACTS="select * from user";

    @Autowired
    JdbcTemplate template;

    public Integer insertContact(Contact contact){
        int updated = template.update(SQL_INSERT_CONTACT,contact.getName(),contact.getEmail(),contact.getMobile());
        return updated;
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactsList = new ArrayList<>();
        int counter=0;

        SqlRowSet rs = template.queryForRowSet(SQL_RETRIEVE_ALL_CONTACTS);
        while(rs.next()){
            try{
                Contact contact = Contact.createContact(rs);
                contactsList.add(contact);
                counter++;
                System.out.println(contact.getName() + " has been retrieved from database " + counter+ "number of times");
            } catch(Exception ex){
                ex.printStackTrace();
            }
            
        }
        return contactsList;
    }
    
}
