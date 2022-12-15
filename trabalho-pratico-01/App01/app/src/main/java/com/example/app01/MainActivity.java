package com.example.app01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText result = findViewById(R.id.text_result);
        result.setVisibility(View.INVISIBLE);
    }

    public void sum(View view) {
        EditText input1 = findViewById(R.id.edittext_01);
        EditText input2 = findViewById(R.id.edittext_02);
        EditText result = findViewById(R.id.text_result);

        int value1 = Integer.parseInt(input1.getText().toString());
        int value2 = Integer.parseInt(input2.getText().toString());

        int result_value = value1 + value2;

        result.setVisibility(View.VISIBLE);
        result.setText(String.valueOf(result_value), TextView.BufferType.EDITABLE);
    }
}