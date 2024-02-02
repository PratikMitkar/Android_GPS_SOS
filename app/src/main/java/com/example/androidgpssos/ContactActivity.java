package com.example.androidgpssos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgpssos.contact.Contact;
import com.example.androidgpssos.contact.ContactDataManager;

import java.util.ArrayList;


public class ContactActivity extends AppCompatActivity {
    private EditText editTextName, editTextPhoneNumber;
    private LinearLayout contactsLayout;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        contactsLayout = findViewById(R.id.contactsLayout);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });

        // Load contacts from storage when the app starts
        contacts = ContactDataManager.loadContacts(this);
        displayContacts();
    }

    private void addContact() {
        String name = editTextName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();

        if (!name.isEmpty() && !phoneNumber.isEmpty()) {
            Contact contact = new Contact(name, phoneNumber);
            contacts.add(contact);

            // Save contacts to storage
            ContactDataManager.saveContacts(this, contacts);

            displayContacts();
            clearInputFields();
        }
    }

    private void displayContacts() {
        contactsLayout.removeAllViews();

        for (Contact contact : contacts) {
            TextView textView = new TextView(this);
            textView.setText("Name: " + contact.getName() + "\nPhone Number: " + contact.getPhoneNumber());
            contactsLayout.addView(textView);

            // You can add an "Edit" button for each contact here
            // to implement the edit functionality.
        }
    }

    private void clearInputFields() {
        editTextName.setText("");
        editTextPhoneNumber.setText("");
    }

    // Implement sending messages here using SMS or any other method.
    // You may need to request SMS permissions in your AndroidManifest.xml file.
}
