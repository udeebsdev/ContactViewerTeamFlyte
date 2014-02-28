package com.example.contactviewer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
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

    public void renderEditContactView(Contact currentContact) {
        EditText editText = (EditText) this.findViewById(R.id.contactName);
        editText.setText(currentContact.name);

        EditText aliasText = (EditText) this.findViewById(R.id.alias);
        aliasText.setText(currentContact.alias);

        EditText businessText = (EditText) this.findViewById(R.id.business_edit);
        businessText.setText(currentContact.business);

        if (currentContact.getPhone() != null) {
            Iterator<String> itr = currentContact.getPhone().keySet().iterator();
            if (itr.hasNext()) {
                String key = itr.next();
                EditText phoneText = (EditText) this.findViewById(R.id.phone_edit);
                phoneText.setText(currentContact.getPhone().get(key));

                Spinner phoneOptions = (Spinner) this.findViewById(R.id.phone_spinner);
                ArrayAdapter adapter = (ArrayAdapter) phoneOptions.getAdapter();
                phoneOptions.setSelection(adapter.getPosition(key));
            }
        }

        if (currentContact.getEmail() != null) {
            Iterator<String> itr = currentContact.getEmail().keySet().iterator();
            if (itr.hasNext()) {
                String key = itr.next();
                EditText emailText = (EditText) this.findViewById(R.id.email_edit);
                emailText.setText(currentContact.getEmail().get(key));

                Spinner emailOptions = (Spinner) this.findViewById(R.id.email_spinner);
                ArrayAdapter adapter = (ArrayAdapter) emailOptions.getAdapter();
                emailOptions.setSelection(adapter.getPosition(key));
            }
        }

        if (currentContact.getAddresses() != null) {
            Iterator<String> itr = currentContact.getAddresses().keySet().iterator();
            if (itr.hasNext()) {
                String key = itr.next();

                Spinner addressOptions = (Spinner) this.findViewById(R.id.address_spinner);
                ArrayAdapter adapter = (ArrayAdapter) addressOptions.getAdapter();
                addressOptions.setSelection(adapter.getPosition(key));

                EditText streetText = (EditText) this.findViewById(R.id.address1_edit);
                EditText cityText = (EditText) this.findViewById(R.id.address_city_edit);
                EditText zipText = (EditText) this.findViewById(R.id.zip_code_edit);
                streetText.setText(currentContact.getAddresses().get(key).getStreet());
                cityText.setText(currentContact.getAddresses().get(key).getCity());
                zipText.setText(currentContact.getAddresses().get(key).getZip());

                Spinner stateOptions = (Spinner) this.findViewById(R.id.state_spinner);
                ArrayAdapter stateAdapter = (ArrayAdapter) stateOptions.getAdapter();
                stateOptions.setSelection(stateAdapter.getPosition(key));
            }
        }

    }

    private void getUpdatedContactInfo(Contact currentContact) {

        EditText editText = (EditText) this.findViewById(R.id.contactName);
        currentContact.setName(editText.getText().toString());

        EditText aliasText = (EditText) this.findViewById(R.id.alias);
        currentContact.setAlias(aliasText.getText().toString());

        EditText businessText = (EditText) this.findViewById(R.id.business_edit);
        currentContact.setBusiness(businessText.getText().toString());

        final EditText phoneText = (EditText) this.findViewById(R.id.phone_edit);
        final Spinner phoneOptions = (Spinner) this.findViewById(R.id.phone_spinner);
        if (currentContact.getPhone() == null) {
            currentContact.setPhone(new HashMap<String, String>());
        } else {

            currentContact.getPhone().clear();
        }
        currentContact.getPhone().put(phoneOptions.getSelectedItem().toString(), phoneText.getText().toString());

        final EditText emailText = (EditText) this.findViewById(R.id.email_edit);
        final Spinner emailOptions = (Spinner) this.findViewById(R.id.email_spinner);
        if (currentContact.getEmail() == null) {
            currentContact.setEmail(new HashMap<String, String>());
        } else {

            currentContact.getEmail().clear();
        }
        currentContact.getEmail().put(emailOptions.getSelectedItem().toString(), emailText.getText().toString());

        final EditText streetText = (EditText) this.findViewById(R.id.address1_edit);
        final EditText cityText = (EditText) this.findViewById(R.id.address_city_edit);
        final EditText zipText = (EditText) this.findViewById(R.id.zip_code_edit);
        final Spinner stateOptions = (Spinner) this.findViewById(R.id.state_spinner);
        final Spinner addressOptions = (Spinner) this.findViewById(R.id.address_spinner);
        if (currentContact.getAddresses() == null) {
            currentContact.setAddresses(new HashMap<String, Address>());
        } else {

            currentContact.getAddresses().clear();
        }
        currentContact.getAddresses().put(addressOptions.getSelectedItem().toString(), new Address(streetText.getText().toString(), cityText.getText().toString(), stateOptions.getSelectedItem().toString(), zipText.getText().toString()));
    }

    public void saveClicked(View currView) {
        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");

        this.getUpdatedContactInfo(currentContact);
        if (null != currentContact.getName() && !currentContact.getName().trim().isEmpty()) {
            List<Contact> contactList = ContactRepository.getContacts(getApplicationContext());
            contactList.set(position, currentContact);
            ContactRepository.saveContacts(getApplicationContext());

            getIntent().putExtra("contact", currentContact);
            setResult(RESULT_OK, getIntent());
            finish();
        } else {
            Toast.makeText(this, "Please enter a name to update the contact", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteClicked(View view) {
        List<Contact> contactList = ContactRepository.getContacts(getApplicationContext());
        Contact contactToBeDeleted = contactList.get(position);
        contactList.remove(contactToBeDeleted);
        ContactRepository.saveContacts(getApplicationContext());

        getIntent().setClass(this, MainActivity.class);
        startActivityForResult(getIntent(), 1);
    }

    public void backToDetailsClicked(View currView) {
        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");
        getIntent().putExtra("contact", currentContact);
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }

}