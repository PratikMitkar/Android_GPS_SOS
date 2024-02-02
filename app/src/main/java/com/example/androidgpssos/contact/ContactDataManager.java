package com.example.androidgpssos.contact;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContactDataManager {
    private static final String PREFS_NAME = "MyContactsPrefs";
    private static final String CONTACTS_KEY = "contacts";

    public static void saveContacts(Context context, ArrayList<Contact> contacts) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String contactsJson = gson.toJson(contacts);

        editor.putString(CONTACTS_KEY, contactsJson);
        editor.apply();
    }

    public static ArrayList<Contact> loadContacts(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String contactsJson = preferences.getString(CONTACTS_KEY, "");

        Type type = new TypeToken<ArrayList<Contact>>() {}.getType();
        ArrayList<Contact> contacts = gson.fromJson(contactsJson, type);

        if (contacts == null) {
            contacts = new ArrayList<>();
        }

        return contacts;
    }
}
