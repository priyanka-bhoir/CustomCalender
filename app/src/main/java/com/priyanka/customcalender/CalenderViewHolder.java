package com.priyanka.customcalender;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView dayOfMonth;
    private final CalenderAdapter.OnItemListner onItemListner;
    public CalenderViewHolder(@NonNull View itemView, CalenderAdapter.OnItemListner onItemListner) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.day_cell);
        this.onItemListner = onItemListner;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onItemListner.onItemClick(getAdapterPosition(),dayOfMonth);
    }
}
