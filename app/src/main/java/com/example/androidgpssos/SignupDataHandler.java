package com.example.androidgpssos;
import android.content.Context;
import android.content.SharedPreferences;

public class SignupDataHandler {

    private static final String PREFERENCE_NAME = "SignupData";

    // Keys for accessing data in SharedPreferences
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private Context context;

    public SignupDataHandler(Context context) {
        this.context = context;
    }

    // Method to save user data
    public void saveUserData(String username, String email, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    // Method to retrieve saved user data
    public UserData getUserData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USERNAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);
        return new UserData(username, email, password);
    }

    // Inner class to represent user data
    public static class UserData {
        private String username;
        private String email;
        private String password;

        public UserData(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
