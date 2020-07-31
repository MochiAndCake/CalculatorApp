package com.example.simple_calculator;

import java.io.*;
import java.util.*;

public class CalculatorFunction {
    private Stack<Character> openBrackets;
    private Stack<Character> closeBrackets;
    private Stack<Character> operators;
    private Stack<Float> numbers;
    private ArrayList<String> brokenExpression;

    private boolean validBrackets(){return false;}

    private boolean precedence(char operator1, char operator2){
        int intValue1 = precedenceValue(operator1);
        int intValue2 = precedenceValue(operator2);
        return intValue1 > intValue2;
    }

    private int precedenceValue(char c){
        int intValue = 0;
        if(c == '*' || c == '/'){
            intValue = 3;
        } else if (c == '+' || c == '-'){
            intValue = 2;
        }
        return intValue;
    }

    /**
     * This private method checks if the character is a sign except minus signs.
     *
     * @param c The character to be checked
     * @return True if the character an operator symbol except minus.
     */
    private boolean signExceptMinus(char c){
        return c == '+' || c == '×' || c == '÷';
    }

    private boolean isNum(char c){
        return Character.isDigit(c);
    }

    // ÷ × - + ( ) 0 1 2 3 4 5 6 7 8 9
    public boolean breakUpEquation(String expression){
        brokenExpression = new ArrayList<String>();
        String strToken = "";
        boolean numHasDecimal = false;
        char char1, char2;

        // Check first character.
        char1 = expression.charAt(0);
        if (signExceptMinus(char1) || char1 == ')'){
            // First character can't be ÷, ×, +, or )
            return false;
        } else if (char1 == '('){
            // Add bracket to the bracket stack
            openBrackets.push(char1);
            brokenExpression.add(Character.toString(char1));
        } else if (char1 == '.'){
            strToken += "0" + char1;
            numHasDecimal = true;
        } else {
            strToken += char1;
        }

        // Checking the other characters in the expression
        for(int i = 1; i < expression.length(); i++){
            char1 = expression.charAt(i-1); // Previous character
            char2 = expression.charAt(i); // Current character

            // Cases that are errors
            if (char2 == '.' && numHasDecimal) {
                // A decimal added to a number that already has a decimal is an error.
                return false;
            } else if (char1 == '-' && char2 == '-' && expression.charAt(i+2) == '-') {
                // 3 negatives in a row is an error.
                return false;
            } else if (char1 == '-' && (signExceptMinus(char2) || char2 == ')')) {
                // If the previous symbol was a negative sign, the current character is a sign, then it is an error.
                // But the reverse is possible.
                return false;
            } else if (signExceptMinus(char1) && signExceptMinus(char2)) {
                // Back to back symbols, excluding negatives, is an error.
                return false;
            } else if ( (signExceptMinus(char1) && char2 == ')') || signExceptMinus(char2) && char1 == '(') {
                // If it is:
                //      Sign & )
                //      ( & Sign
                // Then it is improper.
                return false;
            }

            // Now we will see how to add character to ArrayList.

            // If the current character is a sign... _+
            if(signExceptMinus(char2)){
                if(char1 == ')'){
                } else if (char1 == '.'){
                    strToken += "0";
                    brokenExpression.add(strToken);
                    strToken = "";
                } else {
                    if(strToken.length() > 0){
                        brokenExpression.add(strToken);
                        strToken = "";
                        numHasDecimal = false;
                    }
                }
                brokenExpression.add(Character.toString(char2));
            } else if (char2 == '('){
                if (char1 == '.'){
                    strToken += "0";
                    brokenExpression.add(strToken);
                    strToken = "";
                    brokenExpression.add(Character.toString(char2));
                } else if (isNum(char1) || char1 == ')'){
                    if(strToken.length() > 0){
                        brokenExpression.add(strToken);
                        strToken = "";
                        numHasDecimal = false;
                    }
                    brokenExpression.add("×");
                    brokenExpression.add(Character.toString(char2));
                } else if (signExceptMinus(char1)){
                    brokenExpression.add(Character.toString(char2));
                }
            }


        }
        return false;
    }

    public float logic(String expression){
        return 0f;
    }
}
