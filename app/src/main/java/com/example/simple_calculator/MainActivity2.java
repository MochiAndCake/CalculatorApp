// Ann Soong
package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * This is simple unit converter built on Android.
 * It works such that it takes a value, the unit conversion is selected from drop down boxes,
 * and then the values and units are displayed. The resulting number can be sent back to the
 * calculator.
 *
 * @author Ann Soong
 */
public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String strValue, strResult = "", unit1, unit2;
    private TextView txtScreen;
    private Button buttonSend, buttonSwitch;

    // Constants for unit conversion.
    private final float METRES_TO_FEET = 3.28084f;
    private final float MILES_TO_FEET = 5280;
    private final float GRAMS_TO_POUNDS = 0.00220462f;
    private final float POUNDS_TO_OUNCE = 16f;

    // Used for sending the message back to calculator.
    final String RESULT = "strResult";

    // Spinners are drop down boxes.
    private Spinner dropDownCategory, dropDownConvert;
    // Adapters will take a string array from strings.xml and adapt them into the drop downs.
    private ArrayAdapter<CharSequence> adapterCategory, adapterEmpty, adapterLength, adapterMass, adapterTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("Unit Converter");

        // Intent is set up to receive the data from calculator.
        Intent intent = getIntent();
        String strMessage = intent.getStringExtra(MainActivity.SEND_MESSAGE);

        txtScreen = (TextView) findViewById(R.id.screen);
        txtScreen.setText(strMessage);
        strValue = strMessage;

        // We set up the first drop down as a category selector.
        dropDownCategory = (Spinner) findViewById(R.id.category_spinner);
        dropDownCategory.setOnItemSelectedListener(this);
        adapterCategory = ArrayAdapter.createFromResource(this, R.array.category_array,android.R.layout.simple_spinner_dropdown_item);
        dropDownCategory.setAdapter(adapterCategory);

        // The second drop down will specify the specific units. Its contents depend on the first drop down.
        dropDownConvert = (Spinner) findViewById(R.id.conversion_spinner);
        dropDownConvert.setOnItemSelectedListener(this);
        adapterEmpty = ArrayAdapter.createFromResource(this, R.array.empty_array,android.R.layout.simple_spinner_dropdown_item);
        adapterLength = ArrayAdapter.createFromResource(this, R.array.length_array,android.R.layout.simple_spinner_dropdown_item);
        adapterMass= ArrayAdapter.createFromResource(this, R.array.mass_array,android.R.layout.simple_spinner_dropdown_item);
        adapterTemperature= ArrayAdapter.createFromResource(this, R.array.temperature_array,android.R.layout.simple_spinner_dropdown_item);
        dropDownConvert.setAdapter(adapterEmpty);

        buttonSend = (Button) findViewById(R.id.btn_send);
        buttonSwitch = (Button) findViewById(R.id.btn_switch);

        buttonSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // If the result is empty, then we will replace it with the value we convert from.
                if(strResult.length() <= 0){
                    strResult = strValue;
                }

                // We use intent to send the data back to calculator.
                Intent resultIntent = new Intent();
                resultIntent.putExtra(RESULT, strResult);
                setResult(RESULT_OK, resultIntent);
                finish(); // After the converter sends the data back, it closes.
            }
        });

        buttonSwitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // If the result is not empty, then we can switch the values.
                // This is made so users can select with number to send back by deciding which value is the result.
                if(strResult.length() > 0){
                    String strHold = strResult;
                    strResult = strValue;
                    strValue = strHold;
                    txtScreen.setText(strValue + " " + unit1 + " = " + strResult + " " + unit2);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        float fltValue = 0f, fltResult = 0f;
        unit1 = "";
        unit2 = "";
        // Retrieve the selected item
        String selected = adapterView.getItemAtPosition(position).toString();

        // The following if statements will change the unit conversions available depending on the category chosen.
        if(selected.equals(" ")) {
            dropDownConvert.setAdapter(adapterEmpty);
        } else if(selected.equals("Length")){
            dropDownConvert.setAdapter(adapterLength);
        } else if(selected.equals("Mass")){
            dropDownConvert.setAdapter(adapterMass);
        } else if(selected.equals("Temperature")){
            dropDownConvert.setAdapter(adapterTemperature);
        }

        // Although this is checked in the calculator, it might be best to check the value again.
        // This would come in handy if there are more activities involved.
        try{
            fltValue = Float.parseFloat(strValue);
        } catch (NumberFormatException e){
            txtScreen.setText("ERROR - The value given is invalid.");
        }

        // The following if statements detect which conversion is chosen and calculates the results
        if(selected.equals("Metres to Feet")) {
            fltResult = fltValue * METRES_TO_FEET;
            unit1 = "m"; // We also set the units to display.
            unit2 = "ft";
        } else if(selected.equals("Feet to Metres")){
            fltResult = fltValue / METRES_TO_FEET;
            unit1 = "ft";
            unit2 = "m";
        } else if(selected.equals("Kilometres to Miles")){
            fltResult = fltValue*1000*METRES_TO_FEET/MILES_TO_FEET;
            unit1 = "km";
            unit2 = "mi";
        } else if(selected.equals("Miles to Kilometres")){
            fltResult = ((fltValue * MILES_TO_FEET)/METRES_TO_FEET)/1000;
            unit1 = "mi";
            unit2 = "km";
        } else if (selected.equals("Feet to Miles")){
            fltResult = fltValue / MILES_TO_FEET;
            unit1 = "ft";
            unit2 = "mi";
        } else if (selected.equals("Miles to Feet")){
            fltResult = fltValue * MILES_TO_FEET;
            unit1 = "mi";
            unit2 = "ft";
        } else if(selected.equals("Grams to Pounds")){
            fltResult = fltValue * GRAMS_TO_POUNDS;
            unit1 = "g";
            unit2 = "lb";
        } else if(selected.equals("Pounds to Grams")){
            fltResult = fltValue / GRAMS_TO_POUNDS;
            unit1 = "lb";
            unit2 = "g";
        } else if (selected.equals("Grams to Ounces")){
            fltResult = fltValue * GRAMS_TO_POUNDS * POUNDS_TO_OUNCE;
            unit1 = "g";
            unit2 = "oz";
        } else if (selected.equals("Ounces to Grams")){
            fltResult = (fltValue / POUNDS_TO_OUNCE) / GRAMS_TO_POUNDS;
            unit1 = "oz";
            unit2 = "g";
        } else if (selected.equals("Ounces to Pounds")){
            fltResult = fltValue / POUNDS_TO_OUNCE;
            unit1 = "oz";
            unit2 = "lb";
        } else if (selected.equals("Pounds to Ounces")){
            fltResult = fltValue * POUNDS_TO_OUNCE;
            unit1 = "lb";
            unit2 = "oz";
        } else if(selected.equals("Celsius to Fahrenheit")){
            fltResult = (fltValue * (9.0f/5.0f)) + 32;
            unit1 = "°C";
            unit2 = "°F";
        } else if(selected.equals("Fahrenheit to Celsius")){
            fltResult = (fltValue - 32) * (5.0f/9.0f);
            unit1 = "°F";
            unit2 = "°C";
        }

        // If there no units assigned, that means the drop down selected blank.
        // If so, we display the value only.
        if(unit1.length() <= 0 && unit2.length() <= 0){
            txtScreen.setText(strValue);
        } else {
            // Otherwise, we must display the result and the units.
            strResult = Float.toString(fltResult);
            txtScreen.setText(strValue + " " + unit1 + " = " + strResult + " " + unit2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // If nothing is selected, then we display the value.
        txtScreen.setText(strValue);
    }
}