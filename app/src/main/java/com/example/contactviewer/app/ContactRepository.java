package com.example.contactviewer.app;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactRepository {
    private static List<Contact> contacts;

    public synchronized static List<Contact> getContacts(Context context) {
        if (contacts == null) {
            try {
                File contactsFile = new File(context.getFilesDir(), "contacts.bin");
                if (contactsFile.exists()) {
                    ObjectInputStream ois = null;
                    try {
                        ois = new ObjectInputStream(new FileInputStream(contactsFile));
                        contacts = (List<Contact>) ois.readObject();
                    } finally {
                        if (ois != null) {
                            ois.close();
                        }
                    }
                }
            } catch (Exception e) {
                Toast toast = Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
                toast.show();
            }
        }

        // On first startup, give them some friends
        if (contacts == null) {
            contacts = new ArrayList<Contact>();

            Contact c = new Contact("Joseph Witthuhn", "Thomson Reuters");
            contacts.add(c);

            c = new Contact("Prapti Shrestha", "Vaddio");
            contacts.add(c);

            c = new Contact("Udeeb Shankhadev", "Thomson Reuters");
            contacts.add(c);

            c = new Contact("Prabina Shrestha", "Thomson Reuters");
            contacts.add(c);

            c = new Contact("Gregory Jensen", "Best Buy");
            contacts.add(c);
        }

        Collections.sort(contacts);
        return contacts;
    }

    public synchronized static void saveContacts(Context context) {
        try {
            File contactsFile = new File(context.getFilesDir(), "contacts.bin");

            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(contactsFile));
                oos.writeObject(contacts);
            } finally {
                if (oos != null) {
                    oos.close();
                }
            }
        } catch (Exception e) {
            Toast toast = Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
