package com.example.contactviewer.app;

import java.io.Serializable;
import java.util.HashMap;

public class Contact implements Serializable, Comparable<Contact> {
    String name;
    String business;
    String alias;
    HashMap<String, String> phone;
    HashMap<String, Address> addresses;
    HashMap<String, String> email;

    public Contact(String name, String business) {
        this.name = name;
        this.business = business;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public HashMap<String, String> getPhone() {
        return phone;
    }

    public void setPhone(HashMap<String, String> phone) {
        this.phone = phone;
    }

    public HashMap<String, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(HashMap<String, Address> addresses) {
        this.addresses = addresses;
    }

    public HashMap<String, String> getEmail() {
        return email;
    }

    public void setEmail(HashMap<String, String> email) {
        this.email = email;
    }

    @Override
    public int compareTo(Contact contact) {
        if (name == null && contact.name == null) {
            return 0;
        }

        if (name == null) {
            return -1;
        }

        if (contact.name == null) {
            return 1;
        }

        return name.compareTo(contact.name);
    }
}
