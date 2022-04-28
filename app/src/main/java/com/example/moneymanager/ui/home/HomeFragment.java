package com.example.moneymanager.ui.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.moneymanager.R;


import com.example.moneymanager.database.DatabaseHelper;
import com.example.moneymanager.databinding.FragmentHomeBinding;

import java.util.Calendar;

public class HomeFragment extends Fragment implements View.OnClickListener{

    Button btn_expense, btn_income, btn_save, btn_clear;
    EditText et_note, et_amount;
    TextView tv_inputDate;
    RadioGroup rg_expense1,rg_expense2, rg_income1, rg_income2;
    String category;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initObjects();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //Date picker
        tv_inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                tv_inputDate.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });

        rg_expense1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_clothing:
                        rg_expense2.clearCheck();
                        category = "Clothing";
                        break;

                    case R.id.rb_food:
                        rg_expense2.clearCheck();
                        category = "Food";
                        break;
                }
            }
        });

        rg_expense2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_living:
                        rg_expense1.clearCheck();
                        category = "Living";
                        break;

                    case R.id.rb_transport:
                        rg_expense1.clearCheck();
                        category = "Transport";
                        break;

                }
            }
        });

        rg_income1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_salary:
                        rg_income2.clearCheck();
                        category = "Salary";
                        break;

                    case R.id.rb_investment:
                        rg_income2.clearCheck();
                        category = "Investment";
                        break;
                }
            }
        });

        rg_income2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_bonus:
                        rg_income1.clearCheck();
                        category = "Bonus";
                        break;

                    case R.id.rb_other:
                        rg_income1.clearCheck();
                        category = "Others";
                        break;
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void initObjects(){
        View root = binding.getRoot();
        btn_save = root.findViewById(R.id.btn_save);
        btn_clear = root.findViewById(R.id.btn_clear);
        btn_expense = root.findViewById(R.id.btn_expense);
        btn_income = root.findViewById(R.id.btn_income);
        btn_save.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_expense.setOnClickListener(this);
        btn_income.setOnClickListener(this);

        rg_expense1 = root.findViewById(R.id.rg_expense1);
        rg_expense2 = root.findViewById(R.id.rg_expense2);
        rg_income1 = root.findViewById(R.id.rg_income1);
        rg_income2 = root.findViewById(R.id.rg_income2);


        tv_inputDate = root.findViewById(R.id.tv_InputDate);
        et_note = root.findViewById(R.id.et_note);
        et_amount = root.findViewById(R.id.et_amount);



    }

    @Override
    public void onClick(View view) {
        InputMethodManager manager
                = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);

        switch (view.getId()){
            case R.id.btn_expense:
                rg_expense1.setVisibility(View.VISIBLE);
                rg_expense2.setVisibility(View.VISIBLE);
                rg_income1.setVisibility(View.GONE);
                rg_income2.setVisibility(View.GONE);
                Toast.makeText(getContext().getApplicationContext(),"Expense",Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_income:
                rg_income1.setVisibility(View.VISIBLE);
                rg_income2.setVisibility(View.VISIBLE);
                rg_expense1.setVisibility(View.GONE);
                rg_expense2.setVisibility(View.GONE);
                Toast.makeText(getContext().getApplicationContext(),"Income",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_save:
                DatabaseHelper myDB = new DatabaseHelper(getActivity());
                myDB.addData(tv_inputDate.getText().toString().trim(),
                        et_note.getText().toString().trim(),
                        category,
                        Float.valueOf(et_amount.getText().toString().trim()));
                break;

            case R.id.btn_clear:
                tv_inputDate.setText("");
                et_note.setText(null);
                et_amount.setText(null);

                Toast.makeText(getContext().getApplicationContext(),"Cleared",Toast.LENGTH_SHORT).show();
                break;
        }

    }


}