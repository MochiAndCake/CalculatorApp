package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String value;
    private TextView txtScreen;
    private final float METRES_TO_FEET = 3.28084f;
    private final float MILES_TO_FEET = 5280;
    private final float GRAMS_TO_POUNDS = 0.00220462f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String strMessage = intent.getStringExtra(MainActivity.SEND_MESSAGE);

        txtScreen = (TextView) findViewById(R.id.screen);
        txtScreen.setText(strMessage);
        value = strMessage;

        Spinner dropDown = (Spinner) findViewById(R.id.conversion_spinner);
        dropDown.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.conversions_array,android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // Retrieve the selected item
        String selected = adapterView.getItemAtPosition(position).toString();
        float fltValue = 0f, fltResult = 0f;
        String unit1 = "", unit2 = "";

        try{
            fltValue = Float.parseFloat(value);
        } catch (NumberFormatException e){
            txtScreen.setText("ERROR - The value given is invalid.");
        }

        if(selected.equals("Metres to Feet")) {
            fltResult = fltValue * METRES_TO_FEET;
            unit1 = "m";
            unit2 = "ft";
        } else if(selected.equals("Feet to Metres")){
            fltResult = fltValue / METRES_TO_FEET;
            unit1 = "ft";
            unit2 = "m";
        } else if(selected.equals("Kilometres to Miles")){
            fltResult = ((fltValue/1000)*METRES_TO_FEET/MILES_TO_FEET);
            unit1 = "km";
            unit2 = "mi";
        } else if(selected.equals("Miles to Kilometres")){
            fltResult = ((fltValue * MILES_TO_FEET)/METRES_TO_FEET)/1000;
            unit1 = "mi";
            unit2 = "km";
        } else if(selected.equals("Grams to Pounds")){
            fltResult = fltValue * GRAMS_TO_POUNDS;
            unit1 = "g";
            unit2 = "lb";
        } else if(selected.equals("Pounds to Grams")){
            fltResult = fltValue / GRAMS_TO_POUNDS;
            unit1 = "lb";
            unit2 = "g";
        } else if(selected.equals("Celsius to Fahrenheit")){
            fltResult = (fltValue * (9/5)) + 32;
            unit1 = "°C";
            unit2 = "°F";
        } else if(selected.equals("Fahrenheit to Celsius")){
            fltResult = (fltValue - 32) * (5/9);
            unit1 = "°F";
            unit2 = "°C";
        }

        txtScreen.setText(fltValue + " " + unit1 + " = " + fltResult + " " + unit2);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        txtScreen.setText(value);
    }
}