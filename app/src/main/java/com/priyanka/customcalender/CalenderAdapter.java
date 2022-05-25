package com.priyanka.customcalender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder> {

    private final ArrayList<String> daysOfMOnth;
    private final OnItemListner listner;

    public CalenderAdapter(ArrayList<String> daysOfMOnth, OnItemListner listner) {
        this.daysOfMOnth = daysOfMOnth;
        this.listner = listner;
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_cell,parent,false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.16666666);
        return new CalenderViewHolder(view, listner);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMOnth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysOfMOnth.size();
    }

    public interface OnItemListner{
        void onItemClick(int position, TextView dayText);
    }
}
