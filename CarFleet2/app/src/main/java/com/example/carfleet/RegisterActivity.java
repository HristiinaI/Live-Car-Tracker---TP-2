package com.example.carfleet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextEmail;
    EditText mTextPassword;
    EditText mTextConfirmPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextEmail = (EditText) findViewById(R.id.edittext_email);
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
                if (validate()) {
                    String Email = mTextEmail.getText().toString();
                    String Password = mTextPassword.getText().toString();
                    String Confirm_Password = mTextConfirmPassword.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!db.isEmailExists(Email)) {

                        //Email does not exist now add new user to database
                        db.addUser(new User(null, Email, Password));
                        Snackbar.make(mButtonRegister, "User created successfully! Please Login!", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(mButtonRegister, "User already exists!", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }
    public boolean validate() {
        boolean valid = true;

        //Get values from EditText fields
        String Email = mTextEmail.getText().toString();
        String Password = mTextPassword.getText().toString();
        String Confirm_Password = mTextConfirmPassword.getText().toString();

        /*Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                textInputLayoutUserName.setError(null);
            } else {
                valid = false;
                textInputLayoutUserName.setError("Username is to short!");
            }
        }*/

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
                mTextPassword.setError("Password is to short! Enter at least 5 symbols!");
            }
        }

        if(!Password.equals(Confirm_Password)) {
            valid = false;
            mTextPassword.setError("Passwords does not match!");
        }

        return valid;
    }
}
        /*mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mTextEmail.getText().toString().trim();
                String pass  = mTextPassword.getText().toString().trim();
                String confirm_pass  = mTextConfirmPassword.getText().toString().trim();
                if(validate(email, pass)) {
                    if (pass.equals(confirm_pass)) {
                        long value = db.addUser(email, pass);
                        if (value > 0) {
                            Toast.makeText(RegisterActivity.this, "You have registered successfully!", Toast.LENGTH_SHORT).show();
                            mButtonRegister.setEnabled(false);
                             final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                                     R.style.AppTheme_Dark_Dialog);
                             progressDialog.setIndeterminate(true);
                             progressDialog.setMessage("Creating Account...");
                             progressDialog.show();
                             new Handler().postDelayed(
                                     new Runnable() {
                                         public void run() {
                                             mButtonRegister.setEnabled(true);
                                             setResult(RESULT_OK, null);
                                             finish();
                                             progressDialog.dismiss();
                                         }}, 3000);
                        } else {
                            Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Error!", Toast.LENGTH_SHORT).show();

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