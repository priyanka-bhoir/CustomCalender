package com.priyanka.customcalender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalenderAdapter.OnItemListner {

    TextView pevMonthAction,MYview,nextMonthAction;

    RecyclerView calender;
    TextView red,green,blue;
    private LocalDate selectedDate;
    String Start="",end="";
    ArrayList<TextView> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = LocalDate.now();
        }
        setMonthView();
        arrayList = new ArrayList<>();

    }

    private void setMonthView() {
        MYview.setText(monthYearFromDateView(selectedDate));
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
                if (i<=dayOfWeek || i> daysInMonth+  dayOfWeek){
                    daysInMonthArray.add("");
                }else{
                    daysInMonthArray.add(String.valueOf(i-dayOfWeek));
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
        pevMonthAction =findViewById(R.id.bwd);
        MYview = findViewById(R.id.MYview);
        nextMonthAction = findViewById(R.id.fwd);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);

        calender = findViewById(R.id.recylerview);
    }


    @Override
    public void onItemClick(int position, TextView dayText) {

        if (!dayText.getText().toString().equals("")){
            if (!end.isEmpty()){
                Start ="";
                end = "";
                dayText.setBackgroundColor(Color.TRANSPARENT);
            }
            if (Start.isEmpty()){
                Start = dayText.getText().toString();
            }else {
                end = dayText.getText().toString();
            }
            dayText.setBackgroundColor(Color.DKGRAY);
            arrayList.add(dayText);
        }
    }

    public void pevMonthAction(View view) {
        Start ="";
        end = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        }
    }

    public void nextMonthAction(View view) {
        Start ="";
        end = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
        }
    }

    public void redButton(View view) {
        callSelection("red");

    }
    public void greenClick(View view) {
        callSelection("green");
    }

    public void blueText(View view) {
        callSelection("blue");
    }

    private void callSelection(String red) {
        if (!Start.isEmpty()&&!end.isEmpty()){
            switch (red){
                case "red":
                    for (int i=0;i< arrayList.size();i++){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            arrayList.get(i).setBackgroundColor(getResources().getColor(R.color.red));
                        }
                    }
                    break;
                case "green":
                    for (int i=0;i< arrayList.size();i++){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            arrayList.get(i).setBackgroundColor(getResources().getColor(R.color.greeen));
                        }
                    }
                    break;
                case "blue":
                    for (int i=0;i< arrayList.size();i++){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            arrayList.get(i).setBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    }
                    break;
            }
        }else {
            Toast.makeText(this,"Please select the start and end date",Toast.LENGTH_LONG);
        }
    }


}