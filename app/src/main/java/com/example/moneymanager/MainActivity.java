package com.example.moneymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moneymanager.database.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.moneymanager.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDB;
    ArrayList<String> record_id, record_date, record_note, record_category, record_amount;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_balance, R.id.navigation_report)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        myDB = new DatabaseHelper(MainActivity.this);
        record_id = new ArrayList<>();
        record_date = new ArrayList<>();
        record_note = new ArrayList<>();
        record_category = new ArrayList<>();
        record_amount = new ArrayList<>();
        storeDataInArrays();
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        else{
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
