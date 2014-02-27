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
//    HashMap<String, String> handle;
//    HashMap<String, String> url;
//    List<String> groups;
//    Date dob;
//    String filename;
//    String ringtone;

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

    /*public HashMap<String, String> getHandle() {
        return handle;
    }

    public void setHandle(HashMap<String, String> handle) {
        this.handle = handle;
    }


    public HashMap<String, String> getUrl() {
        return url;
    }

    public void setUrl(HashMap<String, String> url) {
        this.url = url;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRingtone() {
        return ringtone;
    }

    public void setRingtone(String ringtone) {
        this.ringtone = ringtone;
    }
*/
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
