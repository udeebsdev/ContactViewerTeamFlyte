package com.example.contactviewer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        this.renderEditContactView();

    }

    public void renderEditContactView() {
        Button delButton = (Button) this.findViewById(R.id.del_button);
        delButton.setVisibility(View.INVISIBLE);

    }

    public void saveClicked(View currView) {
        Contact currentContact = new Contact();

        this.getUpdatedContactInfo(currentContact);

        List<Contact> contactList = ContactRepository.getContacts(getApplicationContext());
        contactList.add(currentContact);
        ContactRepository.saveContacts(getApplicationContext());

        getIntent().putExtra("contact", currentContact);
        setResult(RESULT_OK, getIntent());
        finish();
    }

    private void getUpdatedContactInfo(Contact currentContact) {

        EditText editText = (EditText) this.findViewById(R.id.contactName);
        currentContact.setName(editText.getText().toString());

        EditText aliasText = (EditText) this.findViewById(R.id.alias);
        currentContact.setAlias(aliasText.getText().toString());

        EditText businessText = (EditText) this.findViewById(R.id.business_edit);
        currentContact.setBusiness(businessText.getText().toString());

    }

    public void deleteClicked(View view) {
    }

    public void backToDetailsClicked(View currView) {
        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");
        getIntent().putExtra("contact", currentContact);
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }
}
