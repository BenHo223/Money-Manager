package com.example.moneymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneymanager.inventory.Type;
import com.example.moneymanager.ui.Balance.BalanceFragment;

import java.util.List;

public class TypeAdapter extends BaseAdapter {
    private Context context;
    private List<Type> typeList;

    public TypeAdapter(BalanceFragment context, List<Type> typeList){
        BalanceFragment.context = context;
        BalanceFragment.typeList = typeList;
    }

    @Override
    public int getCount() {
        return typeList != null ? typeList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_type,viewGroup,false);

        TextView txtName = rootView.findViewById(R.id.name);
        ImageView image = rootView.findViewById(R.id.image);

        txtName.setText(typeList.get(i).getName());
        image.setImageResource(typeList.get(i).getImage());

        return rootView;
    }
}
