package com.example.contactviewer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
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
        if (null!= currentContact.getName() && !currentContact.getName().trim().isEmpty()) {

            List<Contact> contactList = ContactRepository.getContacts(getApplicationContext());
            contactList.add(currentContact);
            ContactRepository.saveContacts(getApplicationContext());

            getIntent().putExtra("contact", currentContact);
            setResult(RESULT_OK, getIntent());
            finish();
        } else {
            Toast.makeText(this,"Please enter a name to create a contact", Toast.LENGTH_SHORT).show();

        }
    }

    private void getUpdatedContactInfo(Contact currentContact){

        EditText editText = (EditText) this.findViewById(R.id.contactName);
        currentContact.setName(editText.getText().toString());

        EditText aliasText = (EditText) this.findViewById(R.id.alias);
        currentContact.setAlias(aliasText.getText().toString());

        EditText businessText = (EditText) this.findViewById(R.id.business_edit);
        currentContact.setBusiness(businessText.getText().toString());

        final EditText phoneText = (EditText) this.findViewById(R.id.phone_edit);
        final Spinner phoneOptions = (Spinner) this.findViewById(R.id.phone_spinner);
        currentContact.setPhone(new HashMap<String, String>());
        currentContact.getPhone().put(phoneOptions.getSelectedItem().toString(), phoneText.getText().toString());

        final EditText emailText = (EditText) this.findViewById(R.id.email_edit);
        final Spinner emailOptions = (Spinner) this.findViewById(R.id.email_spinner);
        currentContact.setEmail(new HashMap<String, String>());
        currentContact.getEmail().put(emailOptions.getSelectedItem().toString(), emailText.getText().toString());

        final EditText streetText = (EditText) this.findViewById(R.id.address1_edit);
        final EditText cityText = (EditText) this.findViewById(R.id.address_city_edit);
        final EditText zipText = (EditText) this.findViewById(R.id.zip_code_edit);
        final Spinner stateOptions = (Spinner) this.findViewById(R.id.state_spinner);
        final Spinner addressOptions = (Spinner) this.findViewById(R.id.address_spinner);
        currentContact.setAddresses(new HashMap<String, Address>());
        currentContact.getAddresses().put(addressOptions.getSelectedItem().toString(), new Address(streetText.getText().toString(), cityText.getText().toString(), stateOptions.getSelectedItem().toString(), zipText.getText().toString()));
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
