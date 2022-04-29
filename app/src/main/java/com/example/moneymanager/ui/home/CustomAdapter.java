package com.example.moneymanager.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseHelper;
import com.example.moneymanager.ui.Balance.BalanceFragment;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    ArrayList record_id, record_date, record_note, record_category, record_amount;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String ID = "KeyId";
    String id;



    public CustomAdapter(Context context,
                         ArrayList record_id, ArrayList record_date, ArrayList record_note, ArrayList record_category, ArrayList record_amount){
        this.activity = activity;
        this.context = context;
        this.record_id = record_id;
        this.record_date = record_date;
        this.record_note = record_note;
        this.record_category = record_category;
        this.record_amount = record_amount;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tv_id.setText(String.valueOf(record_id.get(position)));
        holder.tv_date.setText(String.valueOf(record_date.get(position)));
        holder.tv_note.setText(String.valueOf(record_note.get(position)));
        holder.tv_category.setText(String.valueOf(record_category.get(position)));
        holder.tv_amount.setText(String.valueOf(record_amount.get(position)));

//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(ID, String.valueOf(record_id.get(position)));
//                holder.myDB.deleteOneRow("1");
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return record_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_date, tv_note, tv_category, tv_amount;
        LinearLayout mainLayout;
        DatabaseHelper myDB;
        Button btn_del;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_note = itemView.findViewById(R.id.tv_note);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            mainLayout = itemView.findViewById(R.id.mainLayout);
//            btn_del = itemView.findViewById(R.id.btn_del);

            sharedPreferences = context.getSharedPreferences(mypreference,
                    Context.MODE_PRIVATE);

            sharedPreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            if (sharedPreferences.contains(ID))
                id = sharedPreferences.getString(ID,"");
        }
    }


}


