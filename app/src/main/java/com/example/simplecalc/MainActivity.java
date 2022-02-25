package com.example.simplecalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result_field;
    TextView operation_field;
    EditText number_field;

    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_field.findViewById(R.id.result_field);
        operation_field.findViewById(R.id.operation_field);
        number_field.findViewById(R.id.number_field);

    }
    // сохранение состояния
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("OPERATION", lastOperation);

        if(operand!=null) {
            outState.putDouble("OPERATION", operand);
        }
    }
    // получение ранее сохраненного состояния

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }
}