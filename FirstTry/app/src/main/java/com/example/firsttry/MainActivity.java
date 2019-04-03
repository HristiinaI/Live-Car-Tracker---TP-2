package com.example.firsttry;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    EditText _txtfname, _txtsname, _txtpass, _txtemail, _txtmol, _txtcom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DataBaseHalper(this);

        _btnreg = (Button) findViewById(R.id.btnreg);
        _txtfname = (EditText) findViewById(R.id.txtfname);
        _txtsname = (EditText) findViewById(R.id.txtsname);
        _txtpass = (EditText) findViewById(R.id.txtpass);
        _txtemail = (EditText) findViewById(R.id.txtemail);
        _txtmol = (EditText) findViewById(R.id.txtmol);
        _txtcom = (EditText) findViewById(R.id.txtcom);
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = _txtfname.getText().toString();
                String sname = _txtsname.getText().toString();
                String pass = _txtpass.getText().toString();
                String email = _txtemail.getText().toString();
                String mol = _txtmol.getText().toString();
                String com = _txtcom.getText().toString();
                insertdata(fname, sname, pass, email, mol, com);
                Toast.makeText(getApplicationContext(), "register succesfully", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void insertdata(String fname, String sname, String pass, String email, String mol, String com){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHalper.COL_1, fname);
        contentValues.put(DataBaseHalper.COL_2, sname);
        contentValues.put(DataBaseHalper.COL_3, pass);
        contentValues.put(DataBaseHalper.COL_4, email);
        contentValues.put(DataBaseHalper.COL_5, mol);
        contentValues.put(DataBaseHalper.COL_6, com);

        long id = db.insert(DataBaseHalper.TABLE_NAME, null, contentValues);
    }

}
