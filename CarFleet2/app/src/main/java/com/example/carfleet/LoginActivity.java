package com.example.carfleet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class   LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextEmail;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        mTextEmail = (EditText) findViewById(R.id.edittext_email);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textview_register);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String Email = mTextEmail.getText().toString();
                    String Password = mTextPassword.getText().toString();

                    User currentUser = db.Authenticate(new User(null, Email, Password));

                    if (currentUser != null) {
                        Snackbar.make(mButtonLogin, "Successfully Logged In!", Snackbar.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        //finish();
                    } else {
                        Snackbar.make(mButtonLogin, "Login Error! Please Try Again!", Snackbar.LENGTH_LONG).show();

                    }
                }
            }

        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentMainActivity);
            }
        });

    }
    public boolean validate() {
        boolean valid = true;

        //Get values from EditText fields
        String Email = mTextEmail.getText().toString();
        String Password = mTextPassword.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            mTextEmail.setError("Please enter valid email!");
        } else {
            mTextEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            mTextPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                mTextPassword.setError(null);
            } else {
                valid = false;
                mTextPassword.setError("Password is to short!");
            }
        }

        return valid;
    }
        /*mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mTextEmail.getText().toString().trim();
                String password = mTextPassword.getText().toString().trim();

                if(validate(email, password)) {

                    boolean result = db.checkUser(email, password);

                    if (result) {
                        Intent LoginScreen = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(LoginScreen);
                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                                R.style.AppTheme_Dark_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Authenticating...");
                        progressDialog.show();
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        mButtonLogin.setEnabled(true);
                                        finish();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
                    }else {
                        Toast.makeText(LoginActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    public boolean validate(String email, String password) {
        boolean valid = true;
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mTextEmail.setError("Enter a valid email address!");
            valid = false;
        } else {
            mTextEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mTextPassword.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mTextPassword.setError(null);
        }

        return valid;
    }*/
}
