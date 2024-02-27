package com.example.androidgpssos;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    Button buttonSignUp;
    SignupDataHandler signupDataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        signupDataHandler = new SignupDataHandler(this);


        if(isUserSignedUp()){
            redirectToMainActivity();
        }
        else {
            buttonSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get user input
                    String username = editTextUsername.getText().toString();
                    String email = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();

                    // Store the data
                    signupDataHandler.saveUserData(username, email, password);

                    // Display a confirmation message
                    Toast.makeText(SignupActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();


                }
            });

        }

    }

    private boolean isUserSignedUp() {
        // Check if user data exists
        SignupDataHandler.UserData userData = signupDataHandler.getUserData();
        return userData != null && userData.getUsername() != null && userData.getEmail() != null && userData.getPassword() != null;
    }
    private void redirectToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish SignupActivity to prevent going back to it using the back button
    }
}
