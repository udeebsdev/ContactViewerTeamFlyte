package com.example.contactviewer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent currentIntent = getIntent();
        Contact currentContact = (Contact) currentIntent.getSerializableExtra("contact");

        this.renderEditContactView(currentContact);

    }

    public void renderEditContactView(Contact currentContact){
        EditText editText = (EditText) this.findViewById(R.id.firstname);
        editText.setText(currentContact.name);
    }

    public void saveClicked(View currView){
        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");

        this.getUpdatedContactInfo(currentContact);

        getIntent().putExtra("contact", currentContact);
        setResult(RESULT_OK, getIntent());
        finish();
    }

    private void getUpdatedContactInfo(Contact currentContact){
        EditText editText = (EditText) this.findViewById(R.id.firstname);
        currentContact.setName(editText.getText().toString());

    }

    public void backToDetailsClicked(View currView){
        Contact currentContact = (Contact) getIntent().getSerializableExtra("contact");
        getIntent().putExtra("contact", currentContact);
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}