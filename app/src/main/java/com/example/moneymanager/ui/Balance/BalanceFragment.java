package com.example.moneymanager.ui.Balance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moneymanager.R;
import com.example.moneymanager.databinding.FragmentBalanceBinding;

import java.lang.reflect.Array;

public class BalanceFragment extends Fragment {

    private FragmentBalanceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BalanceViewModel balanceViewModel =
                new ViewModelProvider(this).get(BalanceViewModel.class);

        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        balanceViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
        Spinner balancespinner = (Spinner)root.findViewById(R.id.balancespinner);
        final String[] Type = {"衣", "食", "住", "行", "玩"};
        ArrayAdapter<String> TypeList = new ArrayAdapter<>(BalanceFragment.this,
                android.R.layout.simple_spinner_dropdown_item,Type);
        balancespinner.setAdapter(TypeList);
        balancespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BalanceFragment.this, "您選擇了:" + Type[position], Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
