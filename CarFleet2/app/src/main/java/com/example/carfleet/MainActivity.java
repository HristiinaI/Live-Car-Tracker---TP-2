package com.example.carfleet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton CarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarButton = (ImageButton) findViewById(R.id.car_button);

        CarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadCarActivity = new Intent(MainActivity.this, CarActivity.class);
                startActivity(intentLoadCarActivity);
            }
        });
    }
}
