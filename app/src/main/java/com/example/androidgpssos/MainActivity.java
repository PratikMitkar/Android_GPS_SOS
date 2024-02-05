package com.example.androidgpssos;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    Button addbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbtn = findViewById(R.id.addcont);


        addbtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ContactActivity.class)));

    }
}
