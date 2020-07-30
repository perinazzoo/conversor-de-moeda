package com.gabrielperinazzo.exercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText currentRealInput;
    TextView currentDollarValue, currentEuroValue;
    Button convertButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText realValue = findViewById(R.id.currentRealInput);

        final TextView dollarValue = findViewById(R.id.currentDollarValue);
        final TextView euroValue = findViewById(R.id.currentEuroValue);

        Button btnConvert = findViewById(R.id.convertButton);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringValue = realValue.getText().toString();

                if (stringValue.matches("")) {
                    return;
                }

                Double Value = Double.valueOf(stringValue);

                dollarValue.setText(String.format("%.2f", Value / 4));
                euroValue.setText(String.format("%.2f", Value / 5));
            }
        });
    }
}
