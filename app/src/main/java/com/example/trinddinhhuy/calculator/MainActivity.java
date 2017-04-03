package com.example.trinddinhhuy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9, buttonClear,
            buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEqual;
    private TextView resultText;
    private String displayText, operator, previousOperator;
    private double number1, number2;
    private double result;
    private boolean isNumber1, isButtonEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize
        displayText = "";
        isNumber1 = true;
        operator = "";
        isButtonEnable = false;


        connectToView();

        //set listener to number buttons
        button0.setOnClickListener(new DisplayNumber());
        button1.setOnClickListener(new DisplayNumber());
        button2.setOnClickListener(new DisplayNumber());
        button3.setOnClickListener(new DisplayNumber());
        button4.setOnClickListener(new DisplayNumber());
        button5.setOnClickListener(new DisplayNumber());
        button6.setOnClickListener(new DisplayNumber());
        button7.setOnClickListener(new DisplayNumber());
        button8.setOnClickListener(new DisplayNumber());
        button9.setOnClickListener(new DisplayNumber());

        //set listener to function buttons
            buttonDivide.setOnClickListener(new Calculate());
            buttonMultiply.setOnClickListener(new Calculate());
            buttonPlus.setOnClickListener(new Calculate());
            buttonMinus.setOnClickListener(new Calculate());


        buttonEqual.setOnClickListener(new Calculate());
        buttonClear.setOnClickListener(new Calculate());
    }

    private void connectToView() {
        //Connect number button
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        //Connect function button
        buttonClear = (Button) findViewById(R.id.buttonC);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMultiply = (Button) findViewById(R.id.buttonMulti);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);

        //connect resultText text
        resultText = (TextView) findViewById(R.id.resultView);
    }

    private class DisplayNumber implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Button numberButton = (Button) v;

            isButtonEnable = true;

            if (resultText.equals("0"))
                resultText.setText("");

            //add number to display text
            if (isNumber1) {
                number2 = 0;
                result = 0;
                displayText += numberButton.getText().toString();
                resultText.setText(displayText);
                number1 = Integer.parseInt(displayText);

            } else {

                displayText += numberButton.getText().toString();
                resultText.setText(displayText);
                number2 = Integer.parseInt(displayText);


            }
        }
    }

    private class Calculate implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button functionButton = (Button) v;
            clearDisplay();
            if (v.getId() == R.id.buttonC) {
                reset();
            } else if (v.getId() == R.id.buttonEqual) {
                previousOperator = operator;
                setResultl();

                isNumber1 = true;

            } else {
                if(isButtonEnable) {
                    previousOperator = operator;
                    operator = functionButton.getText().toString();

                    if (previousOperator.equals("") && (operator.equals("*") || operator.equals("/")))
                        number2 = 1;

                    setResultl();
                    isButtonEnable = false;
                }

            }
        }
    }

    private void setResultl() {
        switch (previousOperator) {
            case "+":
                result = Calculator.plus(number1, number2);
                break;
            case "-":
                result = Calculator.minus(number1, number2);
                break;
            case "*":
                result = Calculator.multiply(number1, number2);
                break;
            case "/":
                result = Calculator.divide(number1, number2);
                break;
            default:
                result = number1;
        }
        number1 = result;

        if (isNumber1)
            isNumber1 = !isNumber1;

        resultText.setText(Double.toString(result));


    }


    private void clearDisplay() {
        displayText = "";
    }

    private void resetResult() {
        result = 0;
    }

    private void reset() {
        resultText.setText("0");
        resetResult();
        isNumber1 = true;
        clearDisplay();
        operator = "";
        number1 = 0;
        number2 = 0;
    }
}
