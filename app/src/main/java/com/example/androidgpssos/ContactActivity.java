package com.example.androidgpssos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.androidgpssos.contact.Contact;
import com.example.androidgpssos.contact.ContactDataManager;

import java.util.ArrayList;


public class ContactActivity extends AppCompatActivity {
    private EditText editTextName, editTextPhoneNumber;
    private LinearLayout contactsLayout;
    private ArrayList<Contact> contacts;

    private int editingPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        contactsLayout = findViewById(R.id.contactsLayout);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> addContact());

        // Load contacts from storage when the app starts
        contacts = ContactDataManager.loadContacts(this);
        displayContacts();
    }

    private void addContact() {
        String name = editTextName.getText().toString();
        String phoneNumber = editTextPhoneNumber.getText().toString();

        if (!name.isEmpty() && !phoneNumber.isEmpty()) {
            Contact contact = new Contact(name, phoneNumber);

            if (editingPosition == -1){

                contacts.add(contact);

            }else {
                contacts.set(editingPosition,contact);
                editingPosition = -1;
            }


            // Save contacts to storage
            ContactDataManager.saveContacts(this, contacts);

            displayContacts();
            clearInputFields();
        }
    }

    private void displayContacts() {
        contactsLayout.removeAllViews();

        for(int i = 0; i < contacts.size(); i++){
            final int position = i;
            Contact contact = contacts.get(i);

            TextView textView = new TextView(this);
            textView.setText(String.format("Name : %s\nPhone Number : %s", contact.getName(), contact.getPhoneNumber()));
            contactsLayout.addView(textView);
            Button editbutton = new Button(this);
            editbutton.setText("Edit");
            editbutton.setOnClickListener(v -> showEditDialog(position));
            contactsLayout.addView(editbutton);
            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(v -> showDeleteDialog(position));
            contactsLayout.addView(deleteButton);
        }
    }

    private void clearInputFields() {
        editTextName.setText("");
        editTextPhoneNumber.setText("");
    }
    private void showEditDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Contact");

        // Set up the input
        final EditText inputName = new EditText(this);
        final EditText inputPhoneNumber = new EditText(this);

        inputName.setText(contacts.get(position).getName());
        inputPhoneNumber.setText(contacts.get(position).getPhoneNumber());

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(inputName);
        layout.addView(inputPhoneNumber);
        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = inputName.getText().toString();
            String newPhoneNumber = inputPhoneNumber.getText().toString();

            contacts.set(position, new Contact(newName, newPhoneNumber));
            ContactDataManager.saveContacts(ContactActivity.this, contacts);
            displayContacts();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Contact");
        builder.setMessage("Are you sure you want to delete this contact?");

        // Set up the buttons
        builder.setPositiveButton("Yes", (dialog, which) -> {
            contacts.remove(position);
            ContactDataManager.saveContacts(ContactActivity.this, contacts);
            displayContacts();
        });

        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    // Implement sending messages here using SMS or any other method.
    // You may need to request SMS permissions in your AndroidManifest.xml file.
}
