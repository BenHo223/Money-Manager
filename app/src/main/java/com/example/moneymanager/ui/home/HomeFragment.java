package com.example.moneymanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.moneymanager.R;


import com.example.moneymanager.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements View.OnClickListener{

    Button btn_expense, btn_income, btn_save, btn_clear;
    EditText et_note, et_dollars;
    TextView tv_inputDate;
    RadioGroup rg_expense, rg_income;


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initObjects();



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

        rg_expense = root.findViewById(R.id.rg_expense);
        rg_income = root.findViewById(R.id.rg_income);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_expense:
                rg_expense.setVisibility(View.VISIBLE);
                rg_income.setVisibility(View.GONE);
                break;

            case R.id.btn_income:
                rg_expense.setVisibility(View.GONE);
                rg_income.setVisibility(View.VISIBLE);
        }

    }
}