package com.example.moneymanager.ui.Balance;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import java.util.List;

public class BalanceFragment extends Fragment {


    private FragmentBalanceBinding binding;
    private Spinner spinner_type;

    private TypeAdapter adapter;

    RecyclerView recyclerView;
    private ListView noteListView;

    DatabaseHelper myDB;
    ArrayList<String> id,category,date,note,amount;
    CustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BalanceViewModel balanceViewModel =
                new ViewModelProvider(this).get(BalanceViewModel.class);

        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spinner_type = root.findViewById(R.id.spinner_type);
        recyclerView = root.findViewById(R.id.recyclerView);
        adapter = new TypeAdapter(getActivity(), Data.getTypeList());
        spinner_type.setAdapter(adapter);
        myDB =new DatabaseHelper(getActivity());
        id = new ArrayList<>();
        date = new ArrayList<>();
        note = new ArrayList<>();
        category = new ArrayList<>();
        amount = new ArrayList<>();



        storeDataInArrays();
        customAdapter = new CustomAdapter(getActivity(),id,category,date,amount,note);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


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

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(),"NO data",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                date.add(cursor.getString(3));
                note.add(cursor.getString(2));
                category.add(cursor.getString(4));
                amount.add(cursor.getString(1));
            }

        }
    }
}