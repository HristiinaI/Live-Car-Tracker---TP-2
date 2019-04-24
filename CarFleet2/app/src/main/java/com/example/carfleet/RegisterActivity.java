package com.example.carfleet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextConfirmPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextConfirmPassword = (EditText) findViewById(R.id.edittext_confrim_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pass  = mTextPassword.getText().toString().trim();
                String confirm_pass  = mTextConfirmPassword.getText().toString().trim();

                if(pass.equals(confirm_pass)) {
                    long value = db.addUser(user, pass);
                    if(value > 0) {
                        Toast.makeText(RegisterActivity.this, "You have registered successfully!", Toast.LENGTH_SHORT).show();
                        Intent moteToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moteToLogin);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}