package com.example.samdoel1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectPage extends AppCompatActivity {

    private Button forCarPool;
    private Button forEarn;
    private Button forTracking;
    private Button forCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);

        forCarPool = findViewById(R.id.button3);
        forEarn = findViewById(R.id.button4);
        forTracking = findViewById(R.id.button5);
        forCost = findViewById(R.id.button6);

        forCarPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPage.this, HomeScreen.class);
                startActivity(intent);
            }
        });

        forEarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPage.this, Earning.class);
                startActivity(intent);
            }
        });

        forTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPage.this, Tracking.class);
                startActivity(intent);
            }
        });

        forCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPage.this, Cost.class);
                startActivity(intent);
            }
        });

    }
}