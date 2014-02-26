package com.example.contactviewer.app;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by udeebsdev on 2/14/14.
 */
public class Contact implements Serializable{

    String name;
    String business;
    String alias;
    Map<String, String> phone;
    Map<String, Address> addresses;
    Map<String, String> handle;
    Map<String, String> email;
    Map<String, String> url;
    List<String> groups;
    Date dob;
    String filename;
    String ringtone;

    public Contact(String name, String business, Map<String, String> phone){
        this.name = name;
        this.business = business;
        this.phone = phone;
    }

    public static Contact[] dummyData(){
        return new Contact[]{
                new Contact("Howard", "Soft. Eng.",new HashMap<String, String>(){{put("home","555-555-1234");}}),
                new Contact("Tim", "Sr. Soft. Eng.", new HashMap<String, String>(){{put("home","555-555-3456");}}),
                new Contact("Matt", "Lead Soft. Eng.", new HashMap<String, String>(){{put("home","555-555-5678");}})};
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

    public Map<String, String> getPhone() {
        return phone;
    }

    public void setPhone(Map<String, String> phone) {
        this.phone = phone;
    }

    public Map<String, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<String, Address> addresses) {
        this.addresses = addresses;
    }

    public Map<String, String> getHandle() {
        return handle;
    }

    public void setHandle(Map<String, String> handle) {
        this.handle = handle;
    }

    public Map<String, String> getEmail() {
        return email;
    }

    public void setEmail(Map<String, String> email) {
        this.email = email;
    }

    public Map<String, String> getUrl() {
        return url;
    }

    public void setUrl(Map<String, String> url) {
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


}
