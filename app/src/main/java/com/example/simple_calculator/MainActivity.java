package com.example.simple_calculator;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;

        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

/**
 * This is simple calculator built on Android.
 * It works such that it may only take 2 operands and 1 operator. It cannot perform PEDMAS.
 * When the user attempts to input an equation with 2 operators, the expression will be evaluated
 * before the second operator is appended to the equation.
 *
 * This was for Assignment 3. This program tightly follows the instructions
 * that were given.
 *
 * @author Ann Soong
 */
public class MainActivity extends AppCompatActivity {

    Button button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9,
            buttonAdd, buttonMinus, buttonEqual, buttonDecimal,
            buttonMultiply, buttonDivide, buttonClear,
            buttonUndo, buttonSend;
    TextView txtScreen;
    float val_one, val_two;
    boolean add, sub, mul, div, one_neg, two_neg, one_dec, two_dec;

    boolean numHasDecimal;
    String previousText;
    final static String SEND_MESSAGE = "MESSAGE";

    // one_neg, two_neg are booleans used to keep track of whether
    // operand 1 and operand 2 are negative respectively.

    // one_dec, two_dec are booleans used to prevent the excessive input
    // of decimals on to operand 1 and operand 2 respectively.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.btn0);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);
        button7 = (Button) findViewById(R.id.btn7);
        button8 = (Button) findViewById(R.id.btn8);
        button9 = (Button) findViewById(R.id.btn9);

        txtScreen = (TextView) findViewById(R.id.screen);

        buttonDecimal = (Button) findViewById(R.id.btn_decimal);
        buttonMultiply = (Button) findViewById(R.id.btn_multiply);
        buttonDivide = (Button) findViewById(R.id.btn_divide);
        buttonAdd = (Button) findViewById(R.id.btn_plus);
        buttonMinus = (Button) findViewById(R.id.btn_minus);
        buttonEqual = (Button) findViewById(R.id.btn_equal);
        buttonClear = (Button) findViewById(R.id.btn_clear);
        buttonUndo = (Button) findViewById(R.id.btn_undo);
        buttonSend = (Button) findViewById(R.id.btn_convert);

        // This method is used to initialize the primitive variables used.
        // It may also be used to reset the values of the variables.
        resetGlobalVariables();

        // For each button listener method, it is first checked whether the Error message is
        // currently showing. If it is, then the user should clear the calculator before proceeding.
        button0.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "0");
            }
        });

        button1.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "1");
            }
        });

        button2.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "2");
            }
        });

        button3.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "3");
            }
        });

        button4.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "4");
            }
        });

        button5.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "5");
            }
        });

        button6.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "6");
            }
        });

        button7.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "7");
            }
        });

        button8.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "8");
            }
        });

        button9.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                txtScreen.setText(txtScreen.getText() + "9");
            }
        });

        buttonDecimal.setOnClickListener((v) -> {
            if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                previousText = txtScreen.getText().toString();
                if (numHasDecimal) {
                    txtScreen.setText("SYNTAX ERROR - Please clear.");
                } else if (txtScreen.getText().equals("") || checkIfSymbol(txtScreen.getText().toString().length()-1)) {
                    // If there is currently no numbers inputted, then a 0 is appended before the decimal.
                    txtScreen.setText(txtScreen.getText() + "0.");
                    numHasDecimal = true; // The number now has a decimal.

                } else {
                    // Otherwise, it is safe to append a decimal.
                    txtScreen.setText(txtScreen.getText() + ".");
                    numHasDecimal = true; // The number now has a decimal.
                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the functionality for addition

                if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                    previousText = txtScreen.getText().toString();
                    if(checkIfSymbol(txtScreen.getText().toString().length()-1)){
                        // If a symbol was just added, this is an improper expression.
                        txtScreen.setText("SYNTAX ERROR - Please clear.");
                    } else if(txtScreen.getText() == ""){
                        // If there is currently no text, we append a 0 and then +.
                        add = true;
                        txtScreen.setText("0+");
                    } else {
                        // If there is a previous operator, we evaluate the expression and then append the sign.
                        evaluate();
                        add = true;
                        txtScreen.setText(val_one + "+");
                    }
                }
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the functionality for minus
                String strFull = txtScreen.getText().toString();
                if(!strFull.equals("SYNTAX ERROR - Please clear.")) {
                    if ( (one_neg && strFull.length() == 1) ||
                            (strFull.length() >= 2 && checkIfSymbol(strFull.length()-2) && checkIfSymbol(strFull.length()-1) )) {
                        // There should not be a '-' when:
                        //      First number is negative but number not typed.
                        //      Symbol + symbol (most likely '-') already
                        txtScreen.setText("SYNTAX ERROR - Please clear.");
                    } else if (!one_neg && strFull.length() == 0) {
                        // If there has not been an initial -, then it can be added to make the first number negative.
                        one_neg = true;
                        txtScreen.setText(strFull + "-");
                    } else if (strFull.length() > 1 && checkIfSymbol(strFull.length()-1)) {
                        // If a symbol was just added, then this - will make the second number negative.
                        two_neg = true;
                        txtScreen.setText(strFull + "-");
                    } else if (false) {
                        // If there has not been any other operator, nor is this negative to make operand 1 and operand 2 negative
                        // (I know it's technically: 1 - 1 = 1 + (-1), but this is a very brute way of programming),
                        // then this negative is to signify subtraction.
                        sub = true;
                        txtScreen.setText(strFull + "-");
                    } else {
                        // Otherwise, this negative is the second subtraction. Thus, the first expression will be evaluated.
                        evaluate();
                        sub = true;
                        txtScreen.setText(val_one + "-");
                    }
                }
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the functionality for multiply
                if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                    if(checkIfSymbol(txtScreen.getText().toString().length()-1)){
                        // If a symbol was just appended, then this is an improper expression.
                        txtScreen.setText("SYNTAX ERROR - Please clear.");
                    } else if(txtScreen.getText() == ""){
                        // If the first operand was not given, we append 0 before ×.
                        mul = true;
                        txtScreen.setText("0×");
                    } else if(false){
                        // If there has not been an operator, we don't need to evaluate the expression yet.
                        // The sign gets appended and the boolean adjusted.
                        mul = true;
                        txtScreen.setText(txtScreen.getText() + "×");
                    } else {
                        // Otherwise, this is the second operator and the expression needs to be evaluated first.
                        evaluate();
                        mul = true;
                        txtScreen.setText(val_one + "×");
                    }
                }
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the functionality for division
                if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                    if(checkIfSymbol(txtScreen.getText().toString().length()-1)){
                        // If a symbol was just appended, then this is an improper expression.
                        txtScreen.setText("SYNTAX ERROR - Please clear.");
                    } else if (txtScreen.getText() == ""){
                        // If the first operand was not given, we append 0 before ÷.
                        div = true;
                        txtScreen.setText("0÷");
                    } else if(false){
                        // If there has not been an operator, we don't need to evaluate the expression yet.
                        // The sign gets appended and the boolean adjusted.
                        div = true;
                        txtScreen.setText(txtScreen.getText() + "÷");
                    } else {
                        // Otherwise, this is the second operator and the expression needs to be evaluated first.
                        evaluate();
                        div = true;
                        txtScreen.setText(val_one + "÷");
                    }
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the functionality for equal
                if(!txtScreen.getText().equals("SYNTAX ERROR - Please clear.")) {
                    if(txtScreen.getText() == "" || checkIfSymbol(txtScreen.getText().toString().length()-1) ){
                        // If there has not been any input or if the expression ended on a sign, then it's an improper expression.
                        txtScreen.setText("SYNTAX ERROR - Please clear.");
                    } else if (false) {
                        // If there has been no operator, it implies it is 1 number.
                        txtScreen.setText(txtScreen.getText());
                    } else {
                        // Otherwise, there is an expression to evaluate.
                        evaluate();
                        txtScreen.setText(Float.toString(val_one));
                    }
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the functionality for clear
                resetGlobalVariables(); // We reset the variables.
                txtScreen.setText(""); // The text view is emptied.
            }
        });

        buttonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write the functionality for undo
            }
        });

        buttonSend.setOnClickListener((v) -> {
            Intent intent = new Intent(this, MainActivity2.class);
            String message = txtScreen.getText().toString();
            intent.putExtra(SEND_MESSAGE, message);
            startActivity(intent);
        });
    }

    public void sendNumber(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        String message = txtScreen.getText().toString();
        intent.putExtra(SEND_MESSAGE, message);
        startActivity(intent);
    }

    /**
     * This private method resets/initializes all of the variables important to functionality.
     */
    private void resetGlobalVariables(){
        val_one = 0f;
        val_two = 0f;
        one_neg = false;
        two_neg = false;
        one_dec = false;
        two_dec = false;
        add = false;
        sub = false;
        mul = false;
        div = false;

        numHasDecimal = false;
        previousText = "";
    }

    /**
     * This private method checks if there is a symbol at the index given.
     *
     * @param index The index of the view text to check.
     * @return True if the character at the index is an operator symbol.
     */
    private boolean checkIfSymbol(int index){
        if(index < txtScreen.getText().length()){
            return txtScreen.getText().charAt(index) == '+'
                    || txtScreen.getText().charAt(index) == '-'
                    || txtScreen.getText().charAt(index) == '×'
                    || txtScreen.getText().charAt(index) == '÷'
                    || txtScreen.getText().charAt(index) == '('
                    || txtScreen.getText().charAt(index) == ')';
        }
        return false;
    }

    /**
     * This private method checks if there is a symbol at the index given.
     *
     * @param index The index of the view text to check.
     * @return True if the character at the index is an operator symbol.
     */
    private boolean checkIfSign(int index){
        if(index < txtScreen.getText().length()){
            return txtScreen.getText().charAt(index) == '+'
                    || txtScreen.getText().charAt(index) == '-'
                    || txtScreen.getText().charAt(index) == '×'
                    || txtScreen.getText().charAt(index) == '÷';
        }
        return false;
    }

    /**
     * Determines operand 1 and operand 2 from the text view. It is called in evaluate().
     */
    private void setValues(){
        try{

            String strFull = txtScreen.getText().toString();
            String[] strSplit = new String[0];

            // We split the full string into supposedly 2 entries of a string array.
            // We look for what symbol to split based on the operator booleans.
            if(mul){
                strSplit = strFull.split("×");
            } else if (div){
                strSplit = strFull.split("÷");
            } else if (add){
                // The + symbol brings up errors when splitting.
                // Thus, we replace the + and then split it by the new symbol.
                strFull = strFull.replace("+","&");
                strSplit = strFull.split("&");
            } else if (sub){
                if (one_neg){ // If the first number is negative, we need to reattach the -.
                    strSplit = strFull.substring(1).split("-",2);
                    strSplit[0] = "-" + strSplit[0];
                } else{
                    strSplit = strFull.split("-",2);
                }
            }

            // We parse the strings into the 2 operands.
            val_one = Float.parseFloat(strSplit[0]);
            val_two = Float.parseFloat(strSplit[1]);

        } catch (NumberFormatException e){
            txtScreen.setText("SYNTAX ERROR - Please clear.");
        }
    }

    /**
     * Evaluates the expression currently shown in the app.
     *
     * @return double that represents the answer.
     */
    private double evaluate(){
        setValues(); // Determine the 2 operands.
        // Based on the operator booleans, we determine which operation to use.
        // Once we perform the operation, we reset the boolean to false again.
        // The answer is stored in operand 1 and operand 2 is then reset.
        if(mul) {
            val_one = val_one * val_two;
            mul = false;
        } else if (div) {
            // In division, I tried to catch divide by 0, but floats have unique properties so it was unsuccessful. :(
            if(val_two == 0.0f){
                txtScreen.setText("SYNTAX ERROR - Please clear.");
            }
            val_one = val_one / val_two;
            div = false;
        } else if (add) {
            val_one = val_one + val_two;
            add = false;
        } else if (sub) {
            val_one = val_one - val_two;
            sub = false;
        }

        // Operand 1 may be positive or negative.
        one_neg = (val_one < 0f);
        one_dec = true; // Operand 1 is a float with a decimal
        two_neg = false; // Operand 2 is reset to 0, so it is not negative and does not have a decimal
        two_dec = false;
        val_two = 0f;
        return val_one; // Return answer.
    }
}
