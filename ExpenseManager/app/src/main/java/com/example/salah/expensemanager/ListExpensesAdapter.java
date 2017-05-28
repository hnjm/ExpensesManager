package com.example.salah.expensemanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Salah on 06-Jan-17.
 */

public class ListExpensesAdapter extends BaseAdapter {

    private Context context;
    private List<Expenses> expensesList;

    public ListExpensesAdapter(Context context, List<Expenses> expensesList) {
        this.context = context;
        this.expensesList = expensesList;
    }

    @Override
    public int getCount() {
        return expensesList.size();
    }

    @Override
    public Object getItem(int i) {
        return expensesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return expensesList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.item_listview,null);
        TextView tvItemMoney = (TextView) v.findViewById(R.id.tvItemMoney);
        TextView tvItemCat = (TextView) v.findViewById(R.id.tvItemCat);
        TextView tvItemDate = (TextView) v.findViewById(R.id.tvItemDate);

        tvItemMoney.setText(String.valueOf(expensesList.get(i).getMoney()) + " $");
        tvItemCat.setText(expensesList.get(i).getCategory());
        tvItemDate.setText(expensesList.get(i).getDate());

        return v;
    }
}
