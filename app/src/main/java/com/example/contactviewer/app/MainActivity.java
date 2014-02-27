package com.example.contactviewer.app;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ContactAdapter(
                this, R.layout.contact_item, ContactRepository.getContacts(getApplicationContext())));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Contact contact = (Contact) l.getAdapter().getItem(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("contact", contact);
        intent.putExtra("position", position);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if(resultCode == RESULT_OK){
                List<Contact> contacts = ContactRepository.getContacts(getApplicationContext());
                setListAdapter(new ContactAdapter(this, R.layout.contact_item, contacts));
            }
            if (resultCode == RESULT_CANCELED) {
                // do something here
            }
        }
    }
    class ContactAdapter extends ArrayAdapter<Contact> {

        public ContactAdapter(Context context, int resource, List<Contact> objects) {
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
}
