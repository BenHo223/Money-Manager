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
import com.example.moneymanager.TypeAdapter;
import com.example.moneymanager.databinding.FragmentBalanceBinding;
import com.example.moneymanager.inventory.Data;
import com.example.moneymanager.inventory.Type;

import java.lang.reflect.Array;
import java.util.List;

public class BalanceFragment extends Fragment {

    public static BalanceFragment context;
    public static List<Type> typeList;
    private FragmentBalanceBinding binding;
    private Spinner spinner_type;

    private TypeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BalanceViewModel balanceViewModel =
                new ViewModelProvider(this).get(BalanceViewModel.class);

        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spinner_type = root.findViewById(R.id.spinner_type);

        adapter = new TypeAdapter(BalanceFragment.this, Data.getTypeList());
        spinner_type.setAdapter(adapter);

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
