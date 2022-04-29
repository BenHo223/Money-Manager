package com.example.moneymanager.ui.Balance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
    private  ImageView imageView;
    private FragmentBalanceBinding binding;
    private Spinner spinner_type;

    private TypeAdapter adapter;

    TextView tv_balanceTotal;
    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> record_id, record_date, record_note, record_category, record_amount;
    ImageView btn_delAll;
    CustomAdapter customAdapter;

    float sum = 0;
    String id;
    float TotalClothing1, TotalFood1, TotalLiving1, TotalTransport1, TolatSalary1, TotalInvestment1,TotalBouns1, TotalOthers1, TotalAll1;

    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String ID = "KeyId";
    public static final String TotalClothing = "TotalClothing";
    public static final String TotalFood = "TotalFood";
    public static final String TotalLiving = "TotalLiving";
    public static final String TotalTransport = "TotalTransport";
    public static final String TolatSalary = "TolatSalary";
    public static final String TotalInvestment = "TotalInvestment";
    public static final String TotalBouns = "TotalBouns";
    public static final String TotalOthers = "TotalOthers";
    public static final String TotalAll = "TotalAll";

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
        tv_balanceTotal = root.findViewById(R.id.tv_balanceTotal);
        btn_delAll = root.findViewById(R.id.btn_delAll);
        btn_delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteAllData();
            }
        });

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

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
                        TotalAll1  =  -TotalClothing1 -TotalFood1 -TotalLiving1 -TotalTransport1 + TolatSalary1 + TotalInvestment1 + TotalBouns1 + TotalOthers1;

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));

                        Toast.makeText(adapterView.getContext(), "Select: All", Toast.LENGTH_SHORT).show();
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

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TotalClothing, String.valueOf(sum));
                        TotalClothing1 = sum;
                        Toast.makeText(adapterView.getContext(),"Select: Clothing", Toast.LENGTH_SHORT ).show();
                        break;

                    case 2:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeFoodDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TotalFood,  String.valueOf(sum));
                        TotalFood1 = sum;
                        Toast.makeText(adapterView.getContext(),"Select: Food", Toast.LENGTH_SHORT ).show();
                        break;
                    case 3:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeLivingDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TotalLiving,  String.valueOf(sum));
                        TotalLiving1 = sum;

                        Toast.makeText(adapterView.getContext(),"Select: Living", Toast.LENGTH_SHORT ).show();
                        break;
                    case 4:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeTransportDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TotalTransport,  String.valueOf(sum));
                        TotalTransport1 = sum;

                        Toast.makeText(adapterView.getContext(),"Select: Transport", Toast.LENGTH_SHORT ).show();
                        break;
                    case 5:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeSalaryDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TolatSalary,  String.valueOf(sum));
                        TolatSalary1 = sum;
                        Toast.makeText(adapterView.getContext(),"Select: Salary", Toast.LENGTH_SHORT ).show();
                        break;
                    case 6:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeInvestmentDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TotalInvestment,  String.valueOf(sum));
                        TotalInvestment1 = sum;
                        Toast.makeText(adapterView.getContext(),"Select: Investment", Toast.LENGTH_SHORT ).show();
                        break;
                    case 7:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeBonusDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TotalBouns,  String.valueOf(sum));
                        TotalBouns1 = sum;
                        Toast.makeText(adapterView.getContext(),"Select: Bonus", Toast.LENGTH_SHORT ).show();
                        break;
                    case 8:
                        recyclerView.setAdapter(null);
                        record_id.clear();
                        record_date.clear();
                        record_note.clear();
                        record_category.clear();
                        record_amount.clear();
                        storeOthersDataInArrays();
                        customAdapter = new CustomAdapter(getActivity(),record_id,record_date,record_note,record_category,record_amount);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        sum = 0;
                        for (String tolatAmount : record_amount){
                            sum += Float.parseFloat(tolatAmount);
                        }
                        tv_balanceTotal.setText(Double.toString(sum));
                        editor.putString(TotalOthers,  String.valueOf(sum));
                        TotalOthers1 = sum;
                        Toast.makeText(adapterView.getContext(),"Select: Others", Toast.LENGTH_SHORT ).show();
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

        sharedPreferences = getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);

//        id = sharedPreferences.getString(ID,"");
//        if (sharedPreferences.contains(TotalClothing))
//            TotalClothing1 = Float.parseFloat(sharedPreferences.getString(TotalClothing, ""));
//        if (sharedPreferences.contains(TotalFood))
//            TotalFood1 = Float.parseFloat(sharedPreferences.getString(TotalFood,""));
//        if (sharedPreferences.contains(TotalLiving))
//            TotalLiving1 = Float.parseFloat(sharedPreferences.getString(TotalLiving, ""));
//        if (sharedPreferences.contains(TotalTransport))
//            TotalTransport1 = Float.parseFloat(sharedPreferences.getString(TotalTransport, ""));
//        if (sharedPreferences.contains(TolatSalary))
//            TolatSalary1 = Float.parseFloat(sharedPreferences.getString(TolatSalary, ""));
//        if (sharedPreferences.contains(TotalInvestment))
//            TotalInvestment1 = Float.parseFloat(sharedPreferences.getString(TotalInvestment, ""));
//        if (sharedPreferences.contains(TotalBouns))
//            TotalBouns1 = Float.parseFloat(sharedPreferences.getString(TotalBouns, ""));
//        if (sharedPreferences.contains(TotalOthers))
//            TotalOthers1 = Float.parseFloat(sharedPreferences.getString(TotalOthers, ""));



        return root;

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
    void storeFoodDataInArrays(){
        Cursor cursor = myDB.readFood();
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
    void storeLivingDataInArrays(){
        Cursor cursor = myDB.readLiving();
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
    void storeTransportDataInArrays(){
        Cursor cursor = myDB.readTransport();
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
    void storeSalaryDataInArrays(){
        Cursor cursor = myDB.readSalary();
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
    void storeInvestmentDataInArrays(){
        Cursor cursor = myDB.readInvestment();
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
    void storeBonusDataInArrays(){
        Cursor cursor = myDB.readBonus();
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
    void storeOthersDataInArrays(){
        Cursor cursor = myDB.readOthers();
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