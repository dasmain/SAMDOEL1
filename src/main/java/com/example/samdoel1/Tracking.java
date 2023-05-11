package com.example.samdoel1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Tracking extends AppCompatActivity {

    private EditText routeEditText;
    private EditText delayEditText;
    private Button saveButton;
    private ListView routesListView;
    private ArrayList<String> routesList;
    private ArrayAdapter<String> routesAdapter;
    private SQLiteDatabase transportationDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        routeEditText = findViewById(R.id.editTextTextPersonName5);
        delayEditText = findViewById(R.id.editTextTextPersonName6);
        saveButton = findViewById(R.id.button8);
        routesListView = findViewById(R.id.routesListView);
        routesList = new ArrayList<>();
        routesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, routesList);
        routesListView.setAdapter(routesAdapter);

        transportationDatabase = openOrCreateDatabase("Commute.db", MODE_PRIVATE, null);
        transportationDatabase.execSQL("CREATE TABLE IF NOT EXISTS Routes (Route VARCHAR, Delay VARCHAR);");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String route = routeEditText.getText().toString().trim();
                String delay = delayEditText.getText().toString().trim();

                // insert data into transportation database
                transportationDatabase.execSQL("INSERT INTO Routes VALUES ('" + route + "', '" + delay + "');");

                // clear input fields
                routeEditText.getText().clear();
                delayEditText.getText().clear();

                updateRoutesList();
            }
        });
        updateRoutesList();
    }

    private void updateRoutesList() {
        routesList.clear();

        Cursor cursor = transportationDatabase.rawQuery("SELECT * FROM Routes;", null);
        if (cursor.moveToFirst()) {
            do {
                String route = cursor.getString(0);
                String delay = cursor.getString(1);
                String routeWithDelay = route + " - " + delay;
                routesList.add(routeWithDelay);
            } while (cursor.moveToNext());
        }
        cursor.close();

        routesAdapter.notifyDataSetChanged();
    }
}