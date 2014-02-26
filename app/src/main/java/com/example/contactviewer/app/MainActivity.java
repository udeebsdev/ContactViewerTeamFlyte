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

public class MainActivity extends ListActivity {

    Contact[] contactData = new Contact[]{
            new Contact("Howard", "Soft. Eng.", "howard@howard.com", "555-1234"),
            new Contact("Tim", "Sr. Soft. Eng.", "Tim@Tim.com", "555-1234"),
            new Contact("Matt", "Lead Soft. Eng.", "Matt@Matt.com", "555-1234")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListAdapter(new ContactAdapter(this, R.layout.contact_item, contactData));
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
            titleView.setText(contact.title);

            TextView phoneView = (TextView)rootView.findViewById(R.id.contact_item_phone);
            phoneView.setText(contact.phone);

            return rootView;
        }

    }

}
