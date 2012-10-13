package com.calculator.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.calculator.AndroidCalculatorActivity;
import com.calculator.R;
import com.jayway.android.robotium.solo.Solo;

public class AndroidCalculatorActivityTest extends
        ActivityInstrumentationTestCase2<AndroidCalculatorActivity> {

    private Solo solo;

    public AndroidCalculatorActivityTest() {
        super("com.calculator", AndroidCalculatorActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testDisplayBlackBox() {
        // enter 10 in first editfield
        solo.enterText(0, "10");

        // enter 20 in second editfield
        solo.enterText(1, "20");

        // click on Multiply button
        solo.clickOnButton("Multiply");

        // verify that resultant of 10 x 20
        assertTrue(solo.searchText("200"));
    }

    public void testDisplayWhiteBox() {
        // defining our own values to multiply
        float firstNumber = 10;
        float secondNumber = 20;
        float result = firstNumber * secondNumber;

        // access first value (editField) and putting firstNumber value in it
        EditText firstEditText = (EditText) solo.getView(R.id.EditText01);
        solo.enterText(firstEditText, String.valueOf(firstNumber));

        // access first value (editField) and putting firstNumber value in it
        EditText secondEditText = (EditText) solo.getView(R.id.EditText02);
        solo.enterText(secondEditText, String.valueOf(secondNumber));

        // click on Multiply button
        solo.clickOnButton("Multiply");

        assertTrue(solo.searchText(String.valueOf(result)));

        TextView outputField = (TextView) solo.getView(R.id.TextView01);

        ArrayList<TextView> currentTextViews = solo.getCurrentTextViews(outputField);
        assertFalse(currentTextViews.isEmpty());

        TextView output = currentTextViews.get(0);

        // assert to verify result with visible value
        assertEquals(String.valueOf(result), output.getText().toString());
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
