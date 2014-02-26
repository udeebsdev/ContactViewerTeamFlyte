package com.example.contactviewer.app;

import java.io.Serializable;

/**
 * Created by udeebsdev on 2/14/14.
 */
public class Contact implements Serializable{
    String name;
    String title;
    String email;
    String phone;
    String twitterId;

    public Contact(String name, String title, String email, String phone){
        this.name = name;
        this.title = title;
        this.email = email;
        this.phone = phone;
    }

    public static Contact[] dummyData(){
        return new Contact[]{
                new Contact("Howard", "Soft. Eng.", "howard@howard.com", "555-1234"),
                new Contact("Tim", "Sr. Soft. Eng.", "Tim@Tim.com", "555-1234"),
                new Contact("Matt", "Lead Soft. Eng.", "Matt@Matt.com", "555-1234")
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }
}
