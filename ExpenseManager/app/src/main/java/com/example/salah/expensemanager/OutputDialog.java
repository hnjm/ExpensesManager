package com.example.salah.expensemanager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class OutputDialog extends DialogFragment {

    DBconnection dataBase;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_output,null);


        final EditText etOutput = (EditText) v.findViewById(R.id.etOutputNumber);
        final Spinner spnrCategory1 = (Spinner) v.findViewById(R.id.spnrCategory1);
        Button btnSaveOut = (Button) v.findViewById(R.id.btnSaveOut);

        dataBase = new DBconnection(getActivity());


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrCategory1.setAdapter(adapter);


        btnSaveOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int Mon =  Integer.parseInt("-"+etOutput.getText().toString());
                String Cat = spnrCategory1.getSelectedItem().toString();
                String Dat = formatter.format( new Date());
                dataBase.dataInsert(Mon,Cat,Dat);
                getDialog().dismiss();
            }
        });
        return v;
    }
}
