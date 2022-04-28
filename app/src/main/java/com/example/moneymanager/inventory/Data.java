package com.example.moneymanager.inventory;

import com.example.moneymanager.R;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Type> getTypeList(){
        List<Type> typeList = new ArrayList<>();
        Type All = new Type();
        All.setName("All");
        All.setImage(R.drawable.all);
        typeList.add(All);

        Type Clothes = new Type();
        Clothes.setName("Clothes");
        Clothes.setImage(R.drawable.clothes);
        typeList.add(Clothes);

        Type Food = new Type();
        Food.setName("Food");
        Food.setImage(R.drawable.food);
        typeList.add(Food);

        Type Living = new Type();
        Living.setName("Living");
        Living.setImage(R.drawable.living);
        typeList.add(Living);

        Type Transport = new Type();
        Transport.setName("Transport");
        Transport.setImage(R.drawable.transport);
        typeList.add(Transport);

        Type Salary = new Type();
        Salary.setName("Salary");
        Salary.setImage(R.drawable.salary);
        typeList.add(Salary);

        Type Investment = new Type();
        Investment.setName("Investment");
        Investment.setImage(R.drawable.investment);
        typeList.add(Investment);

        Type Bonus = new Type();
        Bonus.setName("Bonus");
        Bonus.setImage(R.drawable.bonus);
        typeList.add(Bonus);

        Type Others = new Type();
        Others.setName("Others");
        Others.setImage(R.drawable.other);
        typeList.add(Others);

        return typeList;
    }
}
