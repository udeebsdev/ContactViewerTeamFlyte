package com.example.contactviewer.app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends ListActivity {

    Contact[] contactData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new ContactAdapter(this, R.layout.contact_item, this.getContactData()));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Contact contact = (Contact) l.getAdapter().getItem(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("contact", contact);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {

            if(resultCode == RESULT_OK){
                Contact currentContact = (Contact) data.getSerializableExtra("contact");

                contactData[0]=currentContact;

                setListAdapter(new ContactAdapter(this, R.layout.contact_item, contactData));
            }
            if (resultCode == RESULT_CANCELED) {
                // do something here
            }
        }
    }
    class ContactAdapter extends ArrayAdapter<Contact> {

        public ContactAdapter(Context context, int resource, Contact[] objects) {
            super(context, resource, objects);


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rootView = convertView;

            if(rootView == null){
                rootView = getLayoutInflater().inflate(R.layout.contact_item, parent, false);
            }

            Contact contact = getItem(position);

            TextView nameView = (TextView)rootView.findViewById(R.id.contact_item_name);
            nameView.setText(contact.name);

            TextView titleView = (TextView)rootView.findViewById(R.id.contact_item_title);
            titleView.setText(contact.business);

            //TextView phoneView = (TextView)rootView.findViewById(R.id.contact_item_phone);
           // phoneView.setText(contact.getPhone().get("home"));

            return rootView;
        }

    }

    public Contact[] getContactData()
    {
        if(contactData!=null)
        {
           return contactData;
        }
        else
        {
            Contact c1= new Contact("John Smith", "Soft. Eng.");
            c1.setAlias("Johnie");
            /*c1.setPhone(new HashMap<String, String>() {{
                put("home", "555-555-1234");
            }});
            c1.setAddresses(new HashMap<String, Address>() {{
                put("home", new Address("610 Opperman Drive", "Eagan", "MN", "55123"));
            }});
            c1.setEmail(new HashMap<String, String>() {{
                put("personal", "abc@gmail.com");
                put("office", "def@msn.com");
            }});
            c1.setHandle(new HashMap<String, String>() {{
                put("twitter", "@twitterguy");
                put("msn", "@msnguy");
            }});
            c1.setUrl(new HashMap<String, String>(){{put("personal","facebook.com/abc");}});*/

            Contact c2= new Contact("Tim", "Sr. Soft. Eng.");
            Contact c3 = new Contact("Matt", "Lead Soft. Eng.");
            return new Contact[]{c1,c2,c3};
        }
    }

}
