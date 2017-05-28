package com.example.salah.expensemanager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

public class InputDialog extends DialogFragment {

    DBconnection dataBase;
    private DialogInterface.OnDismissListener onDismissListener;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(onDismissListener != null){
            onDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_input,null);

        final EditText etInput = (EditText) v.findViewById(R.id.etInputNumber);

        Button btnSave = (Button) v.findViewById(R.id.btnSaveIn);

        dataBase = new DBconnection(getActivity());


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int Mon =  Integer.parseInt(etInput.getText().toString());
                String Cat = "Income";
                String Dat = formatter.format( new Date());
                dataBase.dataInsert(Mon,Cat,Dat);
                getDialog().dismiss();
            }
        });
        return v;
    }


    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener){
        this.onDismissListener = onDismissListener;
    }
}
