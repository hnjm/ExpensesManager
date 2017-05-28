package com.example.salah.expensemanager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.alorma.timeline.RoundTimelineView;
import com.alorma.timeline.TimelineView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salah on 16-Dec-16.
 */

public class HomeFragment extends Fragment {

    DBconnection dataBase;
    List<Expenses> expenses;
    ListView lvDisplay;
    ListExpensesAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v =inflater.inflate(R.layout.home,container,false);

        dataBase = new DBconnection(getActivity());

        expenses= dataBase.viewMoney();

        lvDisplay = (ListView) v.findViewById(R.id.Display_listview);

        adapter = new ListExpensesAdapter(getActivity(), expenses);

        lvDisplay.setAdapter(adapter);


        final InputDialog d = new InputDialog();
        d.setOnDismissListener(new DialogInterface.OnDismissListener(){
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Fragment frg = getFragmentManager().findFragmentByTag("myTag");
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.detach(frg);
                ft.attach(frg).commit();
            }
        });


        Button Add = (Button) v.findViewById(R.id.btnAdd);
        Button Sub = (Button) v.findViewById(R.id.btnSub);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manger = getFragmentManager();
                InputDialog myDialog = new InputDialog();
                myDialog.show(manger,"MyDialog");
            }
        });

        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manger = getFragmentManager();
                OutputDialog myDialog = new OutputDialog();
                myDialog.show(manger,"MyDialog");
            }
        });


        return v;
    }



}

/*

        ListView list = (ListView) v.findViewById(R.id.Display_listview);

        ArrayAdapter<String> listVieAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuitem
        );
        list.setAdapter(listVieAdapter);
 */
