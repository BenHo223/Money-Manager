package com.example.moneymanager.ui.Balance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moneymanager.MainActivity;
import com.example.moneymanager.R;
import com.example.moneymanager.database.DatabaseHelper;
import com.example.moneymanager.databinding.FragmentBalanceBinding;
import com.example.moneymanager.inventory.Data;
import com.example.moneymanager.inventory.Type;

import java.util.ArrayList;
import java.util.List;

public class BalanceFragment extends Fragment {


    private FragmentBalanceBinding binding;
    private Spinner spinner_type;
    private TypeAdapter adapter;
    private ListView noteListView;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BalanceViewModel balanceViewModel =
                new ViewModelProvider(this).get(BalanceViewModel.class);

        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spinner_type = root.findViewById(R.id.spinner_type);

        adapter = new TypeAdapter(getActivity(), Data.getTypeList());
        spinner_type.setAdapter(adapter);

        //initWidgets();
        //setNoteAdapter();


        return root;
    }


    //private void initWidgets() {
        //noteListView = root.findViewById(R.id.noteListView);
    //}
    //private void setNoteAdapter() {
       // NoteAdapter noteAdapter = new NoteAdapter(getActivity().getApplicationContext(), (List<Note>) Note.noteArrayList);
       // noteListView.setAdapter(noteAdapter);
    //}

    public void newNote(View view){
        Intent newNoteIntent = new Intent(getActivity(), MainActivity.class);
        //starActivity(newNoteIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
