package com.example.carfleet;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CarActivity extends AppCompatActivity {


    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        ListView listView = (ListView) findViewById(R.id.listv);
        final String items[] = new String[] {"Car1"};
        itemList = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtview,itemList);
        final ListView listV=(ListView)findViewById(R.id.listv);
        listV.setAdapter(adapter);
        editText =(EditText)findViewById(R.id.txtInput);
        Button btAdd=(Button)findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem=editText.getText().toString();
                // add new item to arraylist
                itemList.add(newItem);
                // notify listview of data changed
                //adapter.notifyDataSetChanged();
                //adapter = new ArrayAdapter<String>(CarActivity.this, android.R.layout.simple_list_item_1, itemList);
                listV.setAdapter(adapter);
                (editText).setText(" ");
            }

        });

    }
}
