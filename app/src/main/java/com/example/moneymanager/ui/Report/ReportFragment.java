package com.example.moneymanager.ui.Report;

import com.example.moneymanager.R;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ReportFragment
        extends AppCompatActivity {

    // Create the object of TextView
    // and PieChart class
    TextView tvClothing, tvFood, tvLiving, tvTransport, tvSalary, tvInvestment, tvBonus, tvOthers;
    org.eazegraph.lib.charts.PieChart pieChartIn, pieChartEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvClothing = findViewById(R.id.tvClothing);
        tvFood = findViewById(R.id.tvFood);
        tvLiving = findViewById(R.id.tvLiving);
        tvTransport = findViewById(R.id.tvTransport);
        pieChartIn = findViewById(R.id.piechartin);

        // Creating a method setData()
        // to set the text in text view and pie chart


        tvSalary = findViewById(R.id.tvSalary);
        tvInvestment = findViewById(R.id.tvInvestment);
        tvBonus = findViewById(R.id.tvBonus);
        tvOthers = findViewById(R.id.tvOthers);
        pieChartEx = findViewById(R.id.piechartex);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
    }

    private void setData() {

        // Set the percentage of language used
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
}
