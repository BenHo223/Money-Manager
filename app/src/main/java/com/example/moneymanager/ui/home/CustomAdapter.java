package com.example.moneymanager.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    ArrayList id,date,category,note,amount;
    public CustomAdapter(Context context, ArrayList id, ArrayList date, ArrayList note, ArrayList category, ArrayList amount){
        this.context =context;
        this.id = id;
        this.date = date;
        this.note = note;
        this.category = category;
        this.amount = amount;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.category_txt.setText(String.valueOf(category.get(position)));
        holder.note_txt.setText(String.valueOf(note.get(position)));
        holder.amount_txt.setText(String.valueOf(amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt,date_txt,category_txt,note_txt,amount_txt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            category_txt = itemView.findViewById(R.id.category_txt);
            note_txt = itemView.findViewById(R.id.note_txt);
            amount_txt = itemView.findViewById(R.id.amount_txt);
        }
    }
}


