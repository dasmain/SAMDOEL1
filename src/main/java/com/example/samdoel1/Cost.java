package com.example.samdoel1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Cost extends AppCompatActivity {

    private Button forSaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        forSaveBtn = findViewById(R.id.button9);

        forSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Cost.this, "FEATURES HAVE BEEN SAVED!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}