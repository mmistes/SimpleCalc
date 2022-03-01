package com.example.simplecalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result_field;
    EditText number_field;
    TextView operation_field;


    Double operand = null;
    String last_operation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        result_field.findViewById(R.id.result_field);
        number_field.findViewById(R.id.number_field);
        operation_field.findViewById(R.id.operation_field);

    }

    // сохранение состояния
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString("OPERATION", last_operation);

        if (operand != null) {
            outState.putDouble("OPERATION", operand);
        }

        super.onSaveInstanceState(outState);
    }
    // получение ранее сохраненного состояния

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        last_operation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        result_field.setText(operand.toString());
        operation_field.setText(last_operation);
    }


    // Обработка нажатия на числовую кнопку

    public void onClickNumber(View view) {
        Button button = (Button) view;
        number_field.append(button.getText());

        if (last_operation.equals("=") && operand != null) {
            operand = null;
        }
    }

    // Обработка нажатия на кнопку операции
    public void onClickOperation(View view) {

        Button button = (Button) view;
        String operation = button.getText().toString();
        String number = number_field.getText().toString();

        // Если что-то введено
        if (number.length() > 0) {
            try {
                performOperation(Double.valueOf(number), operation);
            } catch (NumberFormatException ex) {
                number_field.setText("");
            }
            last_operation = operation;
            operation_field.setText(last_operation);
        }

    }

    @SuppressLint("SetTextI18n")
    public void performOperation(Double number, String operation) {

        // Если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == 0) {
            operand = number;
        } else {
            if (last_operation.equals("=")) {
                last_operation = operation;
            }
            switch (last_operation) {
                case "C":
                    operand = 0.0;
                    number_field.setText("");
                    operation_field.setText("");
                    break;

                case "⌫":
                    operand = (number == null || number.toString().length() == 0) ? null : Double.valueOf((number.toString().substring(0, number.toString().length() - 1)));
                    break;

                case "=":
                    operand = number;
                    break;

                case "÷":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;

                case "×":
                    operand *= number;
                    break;

                case "-":
                    operand -= number;
                    break;

                case "+":
                    operand += number;
                    break;

                case "%":
                    operand = number / 100;
                    break;

            }
        }
        number_field.setText("");
    }
}