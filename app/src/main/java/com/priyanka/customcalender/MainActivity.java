package com.priyanka.customcalender;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalenderAdapter.OnItemListner {

    TextView monthview;
    TextView yearview;

    RecyclerView calender;
    TextView red,green,blue;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = LocalDate.now();
        }
        setMonthView();


    }

    private void setMonthView() {
        monthview.setText(monthYearFromDateView(selectedDate));
        ArrayList<String> daysInMonth = daysInMontharray(selectedDate);

        CalenderAdapter calenderAdapter = new CalenderAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calender.setLayoutManager(layoutManager);
        calender.setAdapter(calenderAdapter);

    }

    private ArrayList<String> daysInMontharray(LocalDate selectedDate) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            YearMonth yearMonth = YearMonth.from(selectedDate);
            int daysInMonth= yearMonth.lengthOfMonth();
            LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
            int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

            for (int i= 1 ;i<=42; i++){
                if (i<dayOfWeek || i> daysInMonth+  dayOfWeek){
                    daysInMonthArray.add("");
                }else{
                    daysInMonthArray.add(String.valueOf(i+dayOfWeek));
                }
            }
            return daysInMonthArray;
        }
        return daysInMonthArray;
    }

    private String monthYearFromDateView(LocalDate selectedDate) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
            return selectedDate.format(formatter);
        }
        return "";
    }

    private void initWidgets(){
        monthview =findViewById(R.id.monthview);
        yearview = findViewById(R.id.yearView);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);

        calender = findViewById(R.id.recylerview);
    }


    @Override
    public void onItemClick(int position, String dayText) {

    }
}