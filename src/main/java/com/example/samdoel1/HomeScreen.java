package com.example.samdoel1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    private EditText fromEditText;
    private EditText toEditText;
    private Button planTripButton;
    private RadioGroup transportationRadioGroup;
    private Switch saveRouteSwitch;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        fromEditText = findViewById(R.id.fromEditText);
        toEditText = findViewById(R.id.toEditText);
        planTripButton = findViewById(R.id.planTripButton);
        transportationRadioGroup = findViewById(R.id.radioGroup2);
        saveRouteSwitch = findViewById(R.id.saveRouteSwitch);

        String[] arraySpinner = new String[] {
                "Select a Vehicle", "Four Wheels", "Two Wheels"
        };
        Spinner s = (Spinner) findViewById(R.id.vehicleSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        mDatabase = openOrCreateDatabase("Commute.db", MODE_PRIVATE, null);
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS TripInfo (vehicle TEXT, TripFrom TEXT, TripTo TEXT);");
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS FavoriteTrip (vehicle TEXT, TripFrom TEXT, TripTo TEXT);");

        planTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String vehicle = s.getSelectedItem().toString();
                String from = fromEditText.getText().toString();
                String to = toEditText.getText().toString();

                if (from.isEmpty() || to.isEmpty()) {
                    // Fields are empty yo.
                } else {
                    mDatabase.execSQL("INSERT INTO TripInfo (vehicle, TripFrom, TripTo) VALUES (?, ?, ?);", new String[] {vehicle, from, to});
                    Toast.makeText(HomeScreen.this, "TRIP SAVED", Toast.LENGTH_SHORT).show();
                }

                if (saveRouteSwitch.isChecked()) {
                    saveFavoriteRoute(vehicle, from, to);
                }
            }
        });

}

    private void saveFavoriteRoute(String vehicle, String from, String to) {
        mDatabase.execSQL("INSERT INTO FavoriteTrip (vehicle, TripFrom, TripTo) VALUES (?, ?, ?);", new String[] {vehicle, from, to});

    }
    }