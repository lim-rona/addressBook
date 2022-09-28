package com.example.addressBookServer.models;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Contact {
    private String name;
    private String email;
    private String mobile;

    
    public String getName() {
        return name;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Take in a json string and return a Contact object
    public static Contact create(String json){
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject data = reader.readObject();

        final Contact contact = new Contact();
        contact.setName(data.getString("name"));
        System.out.println("Name is: "+ contact.getName());
        System.out.println("MOBILE NUMBER: " + data.getString("mobile"));
        contact.setMobile(data.getString("mobile"));
        if(data.containsKey("email")){
            contact.setEmail(data.getString("email"));
        }else{
            contact.setEmail("");
        }

        return contact;

    }

    public static Contact createContact(SqlRowSet rs) throws SQLException{
        Contact c = new Contact();
        c.setName(rs.getString("name"));
        c.setEmail(rs.getString("email"));
        c.setMobile(rs.getString("mobile"));
        return c;
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
            .add("name",name)
            .add("email",email)
            .add("mobile",mobile)
            .build();
    }
    
    
    
}
