package com.example.salah.expensemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Salah on 07-Apr-17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvMonthIncome;
        public TextView tvMonthOutcome;


        public ViewHolder(final View v) {
            super(v);
            tvMonthIncome=(TextView) v.findViewById(R.id.tv321);
            tvMonthOutcome=(TextView) v.findViewById(R.id.tv123);
        }
    }

    private List<Expenses> mExpenses;
    public RecyclerViewAdapter(List<Expenses> expensess){
        mExpenses=expensess;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recyclerview_blueprint,parent,false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Expenses expenses = mExpenses.get(position);
        TextView MonthlyIncome = holder.tvMonthIncome;
        //MonthlyIncome.setText(expenses.getMoney());

        TextView MonthlyOutcome = holder.tvMonthOutcome;
        //MonthlyOutcome.setText(expenses.getDate());
    }

    @Override
    public int getItemCount() {
        return mExpenses.size();
    }
}
