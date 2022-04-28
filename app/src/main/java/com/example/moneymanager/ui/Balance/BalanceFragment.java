package com.example.moneymanager.ui.Balance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseHelper;
import com.example.moneymanager.databinding.FragmentBalanceBinding;
import com.example.moneymanager.inventory.Data;
import com.example.moneymanager.ui.home.CustomAdapter;

import java.util.ArrayList;

public class BalanceFragment extends Fragment {


    private FragmentBalanceBinding binding;
    private Spinner spinner_type;

    private TypeAdapter adapter;

    RecyclerView recyclerView;

    DatabaseHelper myDB;
    ArrayList<String> record_id, record_date, record_note, record_category, record_amount;
    CustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BalanceViewModel balanceViewModel =
                new ViewModelProvider(this).get(BalanceViewModel.class);

        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spinner_type = root.findViewById(R.id.spinner_type);
        recyclerView = root.findViewById(R.id.recyclerView);
        adapter = new TypeAdapter(getActivity(), Data.getTypeList());
        spinner_type.setAdapter(adapter);


        myDB =new DatabaseHelper(getActivity());
        record_id = new ArrayList<>();
        record_date = new ArrayList<>();
        record_note = new ArrayList<>();
        record_category = new ArrayList<>();
        record_amount = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(getContext(),record_id,record_date,record_note,record_category,record_amount);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(),"NO data",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                record_id.add(cursor.getString(0));
                record_date.add(cursor.getString(1));
                record_note.add(cursor.getString(2));
                record_category.add(cursor.getString(3));
                record_amount.add(cursor.getString(4));
            }

        }
    }
}