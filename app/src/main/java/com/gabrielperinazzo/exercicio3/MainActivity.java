package com.gabrielperinazzo.exercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.*;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView currentDollar, currentEuro;
    EditText currentRealInput;
    Button convertButton, clearButton, devByButton;
    final String[] currentEuroValue = {"..."};
    final String[] currentDollarValue = {"..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
        final EditText realValue = findViewById(R.id.currentRealInput);
//
        final TextView currentDollarView = findViewById(R.id.currentDollar);
        final TextView currentEuroView = findViewById(R.id.currentEuro);
        Button btnConvert = findViewById(R.id.convertButton);
        Button btnClear = findViewById(R.id.clearButton);
        Button btnDev = findViewById(R.id.devByButton);
//
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
//
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realValue.setText("");
                reload();
            }
        });
//
        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Gabriel Perinazzo", Toast.LENGTH_SHORT).show();
            }
        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL currencyEndpoint = new URL("https://economia.awesomeapi.com.br/json/all/USD-BRL,EUR-BRL");


                    HttpsURLConnection myConnection = (HttpsURLConnection) currencyEndpoint.openConnection();

                    if (myConnection.getResponseCode() == 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(myConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line+"\n");
                        }
                        br.close();

                        String json = sb.toString();

                        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
                        currentDollarValue[0] = convertedObject.getAsJsonObject("USD").get("high").getAsString();
                        currentEuroValue[0] = convertedObject.getAsJsonObject("EUR").get("high").getAsString();
                        myConnection.disconnect();

                        currentDollarView.setText("R$ " + currentDollarValue[0]);
                        currentEuroView.setText("R$ " + currentEuroValue[0]);
                    }
                } catch (IOException e) {
                    System.out.println("deu erro");
                }
            }
        });
    }

    public void reload() {
        EditText currentRealInput;
        TextView dollarFinal, euroFinal;
        Button convertButton, clearButton;

        final EditText realValue = findViewById(R.id.currentRealInput);

        final TextView dollarValue = findViewById(R.id.dollarFinal);
        final TextView euroValue = findViewById(R.id.euroFinal);


        String stringValue = realValue.getText().toString();

        if (stringValue.matches("")) {
            dollarValue.setText("");
            euroValue.setText("");
            return;
        }

        Double Value = Double.valueOf(stringValue);

        dollarValue.setText("R$ " + String.format("%.2f", Value / Double.parseDouble(currentDollarValue[0])));
        euroValue.setText("R$ " + String.format("%.2f", Value / Double.parseDouble(currentEuroValue[0])));
    }
}
