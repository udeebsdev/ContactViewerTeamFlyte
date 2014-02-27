package com.example.contactviewer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class EditContactActivity extends Activity {
    private Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent currentIntent = getIntent();
        Contact currentContact = (Contact) currentIntent.getSerializableExtra("contact");
        position = (Integer) currentIntent.getSerializableExtra("position");

        this.renderEditContactView(currentContact);

    }

    public void renderEditContactView(Contact currentContact){
        EditText editText = (EditText) this.findViewById(R.id.firstname);
        editText.setText(currentContact.name);

        EditText aliasText = (EditText) this.findViewById(R.id.alias);
        aliasText.setText(currentContact.alias);

        EditText businessText = (EditText) this.findViewById(R.id.business_edit);
        businessText.setText(currentContact.business);
    }

    public void saveClicked(View currView){
        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");

        this.getUpdatedContactInfo(currentContact);

        List<Contact> contactList = ContactRepository.getContacts(getApplicationContext());
        contactList.set(position, currentContact);
        ContactRepository.saveContacts(getApplicationContext());

        getIntent().putExtra("contact", currentContact);
        setResult(RESULT_OK, getIntent());
        finish();
    }

    private void getUpdatedContactInfo(Contact currentContact){

        EditText editText = (EditText) this.findViewById(R.id.firstname);
        currentContact.setName(editText.getText().toString());

        EditText aliasText = (EditText) this.findViewById(R.id.alias);
        currentContact.setAlias(aliasText.getText().toString());

        EditText businessText = (EditText) this.findViewById(R.id.business_edit);
        currentContact.setBusiness(businessText.getText().toString());

    }

    public void backToDetailsClicked(View currView){
        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");
        getIntent().putExtra("contact", currentContact);
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }

}