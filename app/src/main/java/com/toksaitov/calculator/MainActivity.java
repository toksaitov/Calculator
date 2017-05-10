package com.toksaitov.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText;

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = (EditText) findViewById(R.id.inputEditText);

        calculator = new Calculator();
        updateInputField();
    }

    public void onClearButtonClick(View view) {
        calculator.init();
        updateInputField();
    }

    public void onChangeSignButtonClick(View view) {
        calculator.negate();
        updateInputField();
    }

    public void onNumericButtonClick(View view) {
        String text = ((Button) view).getText().toString();
        calculator.addDigit(Integer.parseInt(text));
        updateInputField();
    }

    public void onDecimalPointButtonClick(View view) {
        calculator.addDecimalPoint();
        updateInputField();
    }

    public void onBinaryOperationButtonClick(View view) {
        String text = view.getTag().toString().toUpperCase();

        try {
            calculator.performBinaryOperation(Calculator.Operation.valueOf(text));
        } catch (ArithmeticException exception) {
            reportError(getString(R.string.DivisionByZeroErrorText));
        }

        updateInputField();
    }

    public void onCalculateResultButtonClick(View view) {
        try {
            calculator.calculate();
        } catch (ArithmeticException exception) {
            reportError(getString(R.string.DivisionByZeroErrorText));
        }

        updateInputField();
    }

    private void updateInputField() {
        inputEditText.setText(calculator.getCurrentValue().toPlainString());
    }

    private void reportError(CharSequence errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}
