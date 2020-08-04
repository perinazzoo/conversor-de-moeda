package com.gabrielperinazzo.exercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText currentRealInput;
    TextView currentDollarValue, currentEuroValue;
    Button convertButton, clearButton, devByButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText currentRealInput;

        final EditText realValue = findViewById(R.id.currentRealInput);

        Button btnConvert = findViewById(R.id.convertButton);
        Button btnClear = findViewById(R.id.clearButton);
        Button btnDev = findViewById(R.id.devByButton);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
                return;
            }
        });
        
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realValue.setText("");
                reload();
                return;
            }
        });

        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Gabriel Perinazzo", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    public void reload() {
        EditText currentRealInput;
        TextView currentDollarValue, currentEuroValue;
        Button convertButton, clearButton;

        final EditText realValue = findViewById(R.id.currentRealInput);

        final TextView dollarValue = findViewById(R.id.currentDollarValue);
        final TextView euroValue = findViewById(R.id.currentEuroValue);


        String stringValue = realValue.getText().toString();

        if (stringValue.matches("")) {
            dollarValue.setText("");
            euroValue.setText("");
            return;
        }

        Double Value = Double.valueOf(stringValue);

        dollarValue.setText(String.format("%.2f", Value / 4));
        euroValue.setText(String.format("%.2f", Value / 5));
    }
}
