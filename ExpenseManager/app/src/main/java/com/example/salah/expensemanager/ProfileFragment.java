package com.example.salah.expensemanager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    DBconnection dataBase;
    ArrayList<String> pos,min,arryRV;
    final String key = "setting";
    final String TAG = "setting";

    TextView tvOutcome,tvIncome,tvTotal,tvUserProfileName;
    CircleImageView imgProfile;
    RecyclerView recyclerView;
    List<Expenses> expenses;
    ListExpensesAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.profile,container,false);

        dataBase = new DBconnection(getActivity());

        recyclerView = (RecyclerView) v.findViewById(R.id.rv);

        expenses= new ArrayList<>(dataBase.viewMoney());

        adapter = new ListExpensesAdapter(getActivity(), expenses);

        RecyclerViewAdapter rvadapter = new RecyclerViewAdapter(expenses);
        recyclerView.setLayoutManager((new LinearLayoutManager(getActivity())));
        recyclerView.setAdapter(rvadapter);




        /*
        dataBase = new DBconnection(getActivity());

        min = dataBase.getMinus();
        pos = dataBase.getPositive();

        int sumArryMin = sumArray(min.toArray(new String[min.size()]));
        int sumArryPos = sumArray(pos.toArray(new String[pos.size()]));
        int total = sumArryPos + sumArryMin;


        String stringMin = Float.toString(sumArryMin);
        String stringPos = Float.toString(sumArryPos);
        String stringTotal = Float.toString(total);


        tvOutcome = (TextView) v.findViewById(R.id.tvOutcome);
        tvIncome = (TextView) v.findViewById(R.id.tvIncome);
        tvTotal = (TextView) v.findViewById(R.id.tvTheTotal);
        tvUserProfileName = (TextView) v.findViewById(R.id.tvUserProfileName);

        tvOutcome.setText(stringMin);
        tvIncome.setText(stringPos);
        tvTotal.setText(stringTotal);

        */


        return v;
    }


    private int sumArray(String[] string) {
        int sum = 0;
        for (int i = 0; i < string.length; i++) {
            sum = sum + Integer.parseInt(string[i]);
        }
        return sum;
    }
}
