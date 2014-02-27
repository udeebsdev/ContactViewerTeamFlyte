package com.example.contactviewer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Iterator;

public class DetailsActivity extends Activity {

    private Contact currentContact;
    private Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.currentContact = (Contact) getIntent().getSerializableExtra("contact");
        this.position = (Integer) getIntent().getSerializableExtra("position");

        this.renderContactDetails();
    }

    public void renderContactDetails(){
        TextView headerView = (TextView)this.findViewById(R.id.header);
        headerView.setText(this.currentContact.name);

        TextView nameView = (TextView)this.findViewById(R.id.contact_name);
        nameView.setText(this.currentContact.name);

        TextView aliasView = (TextView)this.findViewById(R.id.alias_label);
        aliasView.setText(this.currentContact.alias);

        TextView businessView = (TextView)this.findViewById(R.id.business_view);
        businessView.setText(this.currentContact.business);

        if (this.currentContact.getPhone() != null) {
            Iterator<String> itr = this.currentContact.getPhone().keySet().iterator();
            if (itr.hasNext()) {
                String key = itr.next();
                TextView phoneView = (TextView)this.findViewById(R.id.phone_view);
                phoneView.setText(String.format("%s (%s)", this.currentContact.getPhone().get(key), key));
            }
        }

        if (this.currentContact.getEmail() != null) {
            Iterator<String> itr = this.currentContact.getEmail().keySet().iterator();
            if (itr.hasNext()) {
                String key = itr.next();
                TextView emailView = (TextView)this.findViewById(R.id.email_view);
                emailView.setText(String.format("%s (%s)",this.currentContact.getEmail().get(key), key));
            }
        } 
        if (this.currentContact.getAddresses() != null) {
            Iterator<String> itr = this.currentContact.getAddresses().keySet().iterator();
            if (itr.hasNext()) {
                String key = itr.next();
                TextView addressView = (TextView)this.findViewById(R.id.address_view);
                Address address =this.currentContact.getAddresses().get(key);
                addressView.setText(String.format("%s %s %s %s",address.getStreet(), address.getCity(), address.getState(), address.getZip()));
            }
        }
    }

    public void editClicked(View view){
//        View rootView = view;
//
//        if(rootView == null){
//            rootView = getLayoutInflater().inflate(R.layout.activity_details,null);
//        }

        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra("contact", this.currentContact);
        intent.putExtra("position", this.position);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
                this.currentContact = (Contact) data.getSerializableExtra("contact");
                this.position = (Integer) data.getSerializableExtra("position");
                this.renderContactDetails();

                setResult(RESULT_OK, data);
            }
        }
    }
}
