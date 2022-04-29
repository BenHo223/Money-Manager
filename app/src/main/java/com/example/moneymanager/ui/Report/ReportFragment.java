package com.example.moneymanager.ui.Report;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseHelper;
import com.example.moneymanager.databinding.FragmentReportBinding;
import com.example.moneymanager.ui.home.CustomAdapter;

import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class ReportFragment<btn_income2> extends Fragment {

    TextView tvClothing, tvFood, tvLiving, tvTransport, tvSalary, tvInvestment, tvBonus, tvOthers;
    org.eazegraph.lib.charts.PieChart pieChartIn, pieChartEx;
    private FragmentReportBinding binding;
    DatabaseHelper myDB;
    CustomAdapter customAdapter;
//    ArrayList<String> record_date, record_category, tolat_cal_amount;
    Float tolat_cal_amount;
    Button btn_income2, btn_expense2;
    CardView cardViewGraph1, cardViewGraph2, details1, details2;
    float TotalClothing1, TotalFood1, TotalLiving1, TotalTransport1, TolatSalary1, TotalInvestment1,TotalBouns1, TotalOthers1, TotalAll1;

    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String TotalClothing = "TotalClothing";
    public static final String TotalFood = "TotalFood";
    public static final String TotalLiving = "TotalLiving";
    public static final String TotalTransport = "TotalTransport";
    public static final String TolatSalary = "TolatSalary";
    public static final String TotalInvestment = "TotalInvestment";
    public static final String TotalBouns = "TotalBouns";
    public static final String TotalOthers = "TotalOthers";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReportViewModel reportViewModel =
                new ViewModelProvider(this).get(ReportViewModel.class);

        binding = FragmentReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myDB =new DatabaseHelper(getActivity());
        cardViewGraph1 = root.findViewById(R.id.cardViewGraph1);
        cardViewGraph2 = root.findViewById(R.id.cardViewGraph2);
        details1 = root.findViewById(R.id.details1);
        details2 = root.findViewById(R.id.details2);

        btn_expense2 = root.findViewById(R.id.btn_expense2);
        btn_expense2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cardViewGraph1.setVisibility(View.VISIBLE);
                details1.setVisibility(View.VISIBLE);
                cardViewGraph2.setVisibility(View.GONE);
                details2.setVisibility(View.GONE);
                pieChartEx.startAnimation();
                Toast.makeText(getContext().getApplicationContext(),"Expense report",Toast.LENGTH_SHORT).show();
            }
        });
        btn_income2 = root.findViewById(R.id.btn_income2);
        btn_income2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewGraph2.setVisibility(View.VISIBLE);
                details2.setVisibility(View.VISIBLE);
                cardViewGraph1.setVisibility(View.GONE);
                details1.setVisibility(View.GONE);
                pieChartIn.startAnimation();
                Toast.makeText(getContext().getApplicationContext(),"Income report",Toast.LENGTH_SHORT).show();

            }
        });

        sharedPreferences = getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(TotalClothing))
            TotalClothing1 = Float.parseFloat(sharedPreferences.getString(TotalClothing, ""));
        if (sharedPreferences.contains(TotalFood))
            TotalFood1 = Float.parseFloat(sharedPreferences.getString(TotalFood,""));
        if (sharedPreferences.contains(TotalLiving))
            TotalLiving1 = Float.parseFloat(sharedPreferences.getString(TotalLiving, ""));
        if (sharedPreferences.contains(TotalTransport))
            TotalTransport1 = Float.parseFloat(sharedPreferences.getString(TotalTransport, ""));
        if (sharedPreferences.contains(TolatSalary))
            TolatSalary1 = Float.parseFloat(sharedPreferences.getString(TolatSalary, ""));
        if (sharedPreferences.contains(TotalInvestment))
            TotalInvestment1 = Float.parseFloat(sharedPreferences.getString(TotalInvestment, ""));
        if (sharedPreferences.contains(TotalBouns))
            TotalBouns1 = Float.parseFloat(sharedPreferences.getString(TotalBouns, ""));
        if (sharedPreferences.contains(TotalOthers))
            TotalOthers1 = Float.parseFloat(sharedPreferences.getString(TotalOthers, ""));
        Log.d("data ",String.valueOf(TotalClothing1));




        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvClothing = root.findViewById(R.id.tvClothing);
        tvFood = root.findViewById(R.id.tvFood);
        tvLiving = root.findViewById(R.id.tvLiving);
        tvTransport = root.findViewById(R.id.tvTransport);
        pieChartIn = root.findViewById(R.id.piechartin);

        // Creating a method setData()
        // to set the text in text view and pie chart


        tvSalary = root.findViewById(R.id.tvSalary);
        tvInvestment = root.findViewById(R.id.tvInvestment);
        tvBonus = root.findViewById(R.id.tvBonus);
        tvOthers = root.findViewById(R.id.tvOthers);
        pieChartEx = root.findViewById(R.id.piechartex);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();


        return root;
    }

    private void setData() {

        // Set the percentage of language used
        storeTolatAmount("clothing");
        tvClothing.setText(Integer.toString(10));
        tvFood.setText(Integer.toString(25));
        tvLiving.setText(Integer.toString(55));
        tvTransport.setText(Integer.toString(10));

        // Set the data and color to the pie chart
        pieChartIn.addPieSlice(
                new PieModel(
                        "Clothing",
                        Integer.parseInt(tvClothing.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChartIn.addPieSlice(
                new PieModel(
                        "Food",
                        Integer.parseInt(tvFood.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChartIn.addPieSlice(
                new PieModel(
                        "Living",
                        Integer.parseInt(tvLiving.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChartIn.addPieSlice(
                new PieModel(
                        "Transport",
                        Integer.parseInt(tvTransport.getText().toString()),
                        Color.parseColor("#29B6F6")));



        // Set the percentage of language used
        tvSalary.setText(Integer.toString(62));
        tvInvestment.setText(Integer.toString(30));
        tvBonus.setText(Integer.toString(5));
        tvOthers.setText(Integer.toString(3));

        // Set the data and color to the pie chart
        pieChartEx.addPieSlice(
                new PieModel(
                        "Salary",
                        Integer.parseInt(tvSalary.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChartEx.addPieSlice(
                new PieModel(
                        "Investment",
                        Integer.parseInt(tvInvestment.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChartEx.addPieSlice(
                new PieModel(
                        "Bonus",
                        Integer.parseInt(tvBonus.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChartEx.addPieSlice(
                new PieModel(
                        "Others",
                        Integer.parseInt(tvOthers.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChartIn.startAnimation();
        pieChartEx.startAnimation();

    }


    void storeTolatAmount(String category){
        Cursor cursor = myDB.sumAmount("'"+category+"'");
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(),"NO data",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                tolat_cal_amount = cursor.getFloat(0);
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
