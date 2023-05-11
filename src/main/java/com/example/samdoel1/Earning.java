package com.example.samdoel1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Earning extends AppCompatActivity {

    private EditText forFrom;
    private EditText forTo;
    private EditText forFarePrice;
    private Button forOffer;
    private SQLiteDatabase forEarnDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);

        forFrom = findViewById(R.id.editTextTextPersonName2);
        forTo = findViewById(R.id.editTextTextPersonName3);
        forFarePrice = findViewById(R.id.editTextTextPersonName4);
        forOffer = findViewById(R.id.button7);

        forEarnDB = openOrCreateDatabase("Commute.db", MODE_PRIVATE, null);
        forEarnDB.execSQL("CREATE TABLE IF NOT EXISTS Offers (forFROM TEXT, forTo TEXT, FarePrice TEXT);");

        forOffer.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String fFrom = forFrom.getText().toString().trim();
                String fTo = forTo.getText().toString().trim();
                String fFare = forFarePrice.getText().toString().trim();

                forEarnDB.execSQL("INSERT INTO Offers VALUES ('" + fFrom + "', '" + fTo + "', ' " + fFare + "');");
                forFrom.getText().clear();
                forTo.getText().clear();
                forFarePrice.getText().clear();
            }
        });
    }
}