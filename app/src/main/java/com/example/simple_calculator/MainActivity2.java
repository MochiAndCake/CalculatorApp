package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String strMessage = intent.getStringExtra(MainActivity.SEND_MESSAGE);

        TextView txtScreen = (TextView) findViewById(R.id.screen);
        txtScreen.setText(strMessage);
    }
}