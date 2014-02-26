package com.example.contactviewer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    Contact currentContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView textview = (TextView)this.findViewById(R.id.header);
        this.currentContact = (Contact) getIntent().getSerializableExtra("contact");
        textview.setText(currentContact.name);
    }

}
