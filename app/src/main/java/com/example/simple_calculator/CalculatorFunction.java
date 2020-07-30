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
     * This private method checks if there is a symbol at the index given.
     *
     * @param c The index of the view text to check.
     * @return True if the character at the index is an operator symbol.
     */
    private boolean checkIfSymbolExceptMinus(char c){
        return c == '+' || c == '×' || c == '÷';
    }

    // ÷×-+
    public boolean breakUpEquation(String expression){
        brokenExpression = new ArrayList<String>();
        String strWord = "";
        boolean wordHasDecimal = false;
        char char1, char2;

        char1 = expression.charAt(0);

        // Check first character.
        if (checkIfSymbolExceptMinus(char1) || char1 == ')'){
            // First character
            return false;
        } else if (char1 == '('){
            // Add bracket to the bracket stack
            openBrackets.push(char1);
            brokenExpression.add(Character.toString(char1));
        } else if (char1 == '-' || char1 == '.'){
            strWord += char1;
            wordHasDecimal = char1 == '.';
        }

        for(int i = 1; i < expression.length(); i++){
            char1 = expression.charAt(i-1);
            char2 = expression.charAt(i);

            if (char2 == '.' && (wordHasDecimal || char1 == '.')) {
                // 2 decimals in a row or a decimal added to a number that already has a decimal is an error.
                return false;
            } else if (char2 == '-' && char1 == '-' && expression.charAt(i+2) == '-') {
                // 3 negatives in a row is an error.
                return false;
            } else if (char1 == '-' && checkIfSymbolExceptMinus(char2)) {
                // If the previous symbol was a negative sign, the current character is a sign, then it is an error.
                return false;
            } else if (checkIfSymbolExceptMinus(char1) && checkIfSymbolExceptMinus(char2)){
                // Back to back symbols, excluding negatives, is an error.
                return false;
            } else if (char2 == '('){
                openBrackets.push(char2);
            } else if (char2 == ')'){
                closeBrackets.push(char2);
            }

            if( char1 == '-' ){

            } else if (Character.isDigit(char1) && !Character.isDigit(char2) && char2 != '.'){
                //brokenExpression.add(expression.substring(i,j));
                brokenExpression.add(Character.toString(char2));
                //i = j;
            } else if ( Character.isDigit(char1) && Character.isDigit(char2) ){
                strWord += char2;
            }

            wordHasDecimal = char2 == '.';
        }
        return false;
    }

    public float logic(String expression){
        return 0f;
    }
}
