# CalculatorApp

Author: Ann Soong

The objective of this project is to create an android app that acts as calculator and a basic unit
converter. It was built off a previous project called Simple_Calculator. The functionality of the
calculator remains simple.

USER CASES:

1. When the user clicks the "Undo" button, the app will display the text before the user's last
    input, unless the user's last input was cleared. However, the undo button will only undo one
    step.

2. When the user clicks on the "Convert" button if they have a number on the screen, the app will
    switch to the Unit Conversion screen. The number they had on the Calculator screen will appear
    on the Unit Conversion screen.

3. On the Unit Conversion screen, the user will see two drop down menus. The user will select a
    category of conversion in the first drop down and the options will change in the second drop
    down.

4. When the user chooses an option of conversion, the original number and unit and the resulting
    number and unit will be displayed.

5. From the unit conversion screen, the user can click "Send Result". The resulting number from the
    conversion will be sent back to the calculator.

6. The user can click the "Switch Values" button to switch the numbers in the converter.



IMPORTANT FILES:
    MainActivity
        This Java file contains the code of the calculator. The calculator is simple. When there are
        2 number in the valid equation, the value will be calculated when you next enter an operator
        or when you hit "Equals". The calculator has an "Undo" button that will take you back a step.
        The calculator also has a "Convert" button that will send you to the unit converter.

    MainActivity2
        This Java file contains the code of the unit converter. It has 2 



HOW TO RUN THE PROGRAM:
Since this program is an android app, it can be run from Android Studio or it can be run on an emulator.
It can be run from commandline as well, but you will need to




Sources:
Learned how to pass data between activities from this informative video: https://www.youtube.com/watch?v=AD5qt7xoUU8
