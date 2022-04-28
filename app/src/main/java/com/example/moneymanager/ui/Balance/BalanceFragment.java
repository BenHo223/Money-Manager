package com.example.moneymanager.ui.Balance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.example.moneymanager.inventory.Type;
import com.example.moneymanager.ui.home.CustomAdapter;

import java.util.ArrayList;

public class BalanceFragment extends Fragment {
    private ImageView imageView;

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
        imageView = root.findViewById(R.id.image);

        spinner_type = root.findViewById(R.id.spinner_type);
        spinner_type.setAdapter(adapter);
        recyclerView = root.findViewById(R.id.recyclerView);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeAllDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        Toast.makeText(adapterView.getContext(), "Select: 0", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeClothesDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                        Toast.makeText(adapterView.getContext(),"Select: 1", Toast.LENGTH_LONG ).show();
                        break;

                    case 2:
                        Toast.makeText(adapterView.getContext(),"Select: 2", Toast.LENGTH_LONG ).show();
                        break;
                    case 3:
                        Toast.makeText(adapterView.getContext(),"Select: 3", Toast.LENGTH_LONG ).show();
                        break;
                    case 4:
                        Toast.makeText(adapterView.getContext(),"Select: 4", Toast.LENGTH_LONG ).show();
                        break;
                    case 5:
                        Toast.makeText(adapterView.getContext(),"Select: 5", Toast.LENGTH_LONG ).show();
                        break;
                    case 6:
                        Toast.makeText(adapterView.getContext(),"Select: 6", Toast.LENGTH_LONG ).show();
                        break;
                    case 7:
                        Toast.makeText(adapterView.getContext(),"Select: 7", Toast.LENGTH_LONG ).show();
                        break;
                    case 8:
                        Toast.makeText(adapterView.getContext(),"Select: 8", Toast.LENGTH_LONG ).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        adapter = new TypeAdapter(getActivity(), Data.getTypeList());
        spinner_type.setAdapter(adapter);


        myDB =new DatabaseHelper(getActivity());
        record_id = new ArrayList<>();
        record_date = new ArrayList<>();
        record_note = new ArrayList<>();
        record_category = new ArrayList<>();
        record_amount = new ArrayList<>();





        return root;

    }
    public void checkSameDate(){

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void storeAllDataInArrays(){
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

    void storeClothesDataInArrays(){
        Cursor cursor = myDB.readClothes();
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