package com.example.samdoel1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView forErrors;
    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;
    private Button mRegisteredButton;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.usernamefield);
        mPassword = findViewById(R.id.passfield);
        mLoginButton = findViewById(R.id.loginButton);
        mRegisteredButton = findViewById(R.id.button2);
        forErrors = findViewById(R.id.textView6);

        mDatabase = openOrCreateDatabase("Commute.db", MODE_PRIVATE, null);
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS userInfo (username TEXT, password TEXT, type TEXT);");

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                Cursor cursor = mDatabase.rawQuery("SELECT * FROM userInfo WHERE username=? AND password=?;", new String[] {username, password});
                if (cursor.getCount() > 0) {
                    Intent intent = new Intent(MainActivity.this, SelectPage.class);
                    startActivity(intent);
                } else {
                    forErrors.setText("USER DOES NOT EXIST.");
                }
                cursor.close();
            }
        });

        mRegisteredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register_activity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        mDatabase.close();
        super.onDestroy();
    }
}