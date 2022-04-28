package com.example.moneymanager.ui.Report;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseHelper;
import com.example.moneymanager.databinding.FragmentReportBinding;
import com.example.moneymanager.ui.home.CustomAdapter;

import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class ReportFragment extends Fragment {

    TextView tvClothing, tvFood, tvLiving, tvTransport, tvSalary, tvInvestment, tvBonus, tvOthers;
    org.eazegraph.lib.charts.PieChart pieChartIn, pieChartEx;
    private FragmentReportBinding binding;
    DatabaseHelper myDB;
    CustomAdapter customAdapter;
//    ArrayList<String> record_date, record_category, tolat_cal_amount;
    Float tolat_cal_amount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReportViewModel reportViewModel =
                new ViewModelProvider(this).get(ReportViewModel.class);

        binding = FragmentReportBinding.inflate(inflater, container, false);

        myDB =new DatabaseHelper(getActivity());

        View root = binding.getRoot();
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
        tvClothing.setText(Integer.toString(25));
        tvFood.setText(Integer.toString(25));
        tvLiving.setText(Integer.toString(25));
        tvTransport.setText(Integer.toString(25));

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
        tvSalary.setText(Integer.toString(25));
        tvInvestment.setText(Integer.toString(25));
        tvBonus.setText(Integer.toString(25));
        tvOthers.setText(Integer.toString(25));

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
