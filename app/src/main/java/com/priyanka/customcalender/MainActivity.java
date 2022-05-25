package com.priyanka.customcalender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView monthview;
    TextView yearview;

    FrameLayout calender;
    TextView red,green,blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthview =findViewById(R.id.monthview);
        yearview = findViewById(R.id.yearView);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);

        calender = findViewById(R.id.calender);



    }
}