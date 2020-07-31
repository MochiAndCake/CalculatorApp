# CalculatorApp

Author: Ann Soong

The objective of this project is to create an android app that acts as calculator and a basic unit
converter. It was built off a previous project called Simple_Calculator. The functionality of the
calculator remains simple.
The android app has the logic coded in Java files and the user interfaces are coded in XML files.

![State diagram](https://github.com/MochiAndCake/CalculatorApp/blob/master/statediagram.png)



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



GOING FORWARD:

    Going forward, more features can be added to this project. Both the unit converter and the
    calculator can include more features. The unit converter could benefit from adding an activity
    as a transition in from the calculator to the converter that would allow users to select the
    category of units. Having a separate activity could be more visually appealing to users. More
    categories can be included as well. The converter could benefit from having two separate drop
    downs to choose what unit to convert from and what unit to convert to.



IMPORTANT FILES:

    MainActivity
        This Java file contains the code of the calculator. The calculator is simple. When there are
        2 number in the valid equation, the value will be calculated when you next enter an operator
        or when you hit "Equals". The calculator has an "Undo" button that will take you back a step.
        The calculator also has a "Convert" button that will send you to the unit converter.

        Location: CalculatorApp/app/src/main/java/com/example/simple_calculator/MainActivity.java

    MainActivity2
        This Java file contains the code of the unit converter. It will receive the number that was
        in the calculator. It has 2 drop down boxes in the user interface. The first drop down box
        chooses the category of units. The second drop down box will adjust according to the first
        one. It will include the unit conversions. There is a button to switch the numbers of the
        value to be converted and the result. Lastly, the result can be sent back to the calculator
        for more calculations.

        Location: CalculatorApp/app/src/main/java/com/example/simple_calculator/MainActivity2.java

    strings.xml
        This XML file contains important String arrays that are used for the drop down boxes found
        in the converter (MainActivity2).

        Location: CalculatorApp/app/src/main/res/values/strings.xml

    activity_main.xml
        This XML file contains code used to create the user interface for the calculator.

        Location: CalculatorApp/app/src/main/res/layout/activity_main.xml

    activity_main2.xml
        This XML file contains code used to create the user interface for the unit converter.

        Location: CalculatorApp/app/src/main/res/layout/activity_main2.xml



HOW TO RUN THE PROGRAM:

    Since this program is an android app, it can be run from Android Studio or it can be run on an
    emulator.

    Emulators will require the APK file. This can be found at:
        CalculatorApp/app/build/outputs/apk/debug/app-debug.apk
        [GitHub]

1. Install Android Studio. You can download Android Studio from here:
    [GitHub] https://developer.android.com/studio

2. Open the project in Android Studio.

3. Select Tools>Android>AVD Manager.

4. Click on "Create Virtual Device".

5. Under "Category", choose "Phone" and select any of the options. Click "Next".

6. Select an API. Click "Next".

7. Name your AVD and click "Finish".

8. To run the program, click the hammer icon (Tooltip: Make Project) at the bar on the top of the
    screen. When the build is finished, click on the play icon (Tooltip: Run 'app').



Sources:
    Learned how to pass data between activities from this informative video:
    https://www.youtube.com/watch?v=AD5qt7xoUU8
