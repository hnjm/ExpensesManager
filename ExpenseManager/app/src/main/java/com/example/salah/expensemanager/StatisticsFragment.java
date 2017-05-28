package com.example.salah.expensemanager;

import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;



public class StatisticsFragment extends Fragment {

    DBconnection dataBase;
    ArrayList<String> arryFD;
    ArrayList<String> arryRent;
    ArrayList<String> arryAT;
    ArrayList<String> arryShop;
    ArrayList<String> arryBU;
    ArrayList<String> arryBS;
    ArrayList<String> arryHF;
    String FD = "Food and Dining";
    String Rent = "Rents";
    String AT = "Auto and Transport";
    String Shop = "Shopping";
    String BU = "Bills and Utilities";
    String BS = "Business Services";
    String HF = "Health and Fitness";
    private static String TAG = "Statistics";
    public int[] yData;
    private String[] xData = {"Food and Dining","Rents","Auto and Transport","Shopping","Bills and Utilities","Business Services","Health and Fitness"};
    PieChart pieChart;
    TextView tvPieChart;
    


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.statistics,container,false);

        tvPieChart = (TextView) v.findViewById(R.id.tvPiechart);
        dataBase = new DBconnection(getActivity());

        arryFD = dataBase.searchCategory(FD);
        arryRent = dataBase.searchCategory(Rent);
        arryAT = dataBase.searchCategory(AT);
        arryShop = dataBase.searchCategory(Shop);
        arryBU = dataBase.searchCategory(BU);
        arryBS = dataBase.searchCategory(BS);
        arryHF = dataBase.searchCategory(HF);

        int sumFD = sumArray(arryFD.toArray(new String[arryFD.size()]));
        int sumRent = sumArray(arryRent.toArray(new String[arryRent.size()]));
        int sumAT = sumArray(arryAT.toArray(new String[arryAT.size()]));
        int sumShop = sumArray(arryShop.toArray(new String[arryShop.size()]));
        int sumBU = sumArray(arryBU.toArray(new String[arryBU.size()]));
        int sumBS = sumArray(arryBS.toArray(new String[arryBS.size()]));
        int sumHF = sumArray(arryHF.toArray(new String[arryHF.size()]));


        int[] a = {sumFD,sumRent,sumAT,sumShop,sumBU,sumBS,sumHF};

        yData= a;

        Log.d(TAG, "onCreateView: Starting to creet chart");

        pieChart = (PieChart) v.findViewById(R.id.idPieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleColor(Color.rgb(30,34,40));
        pieChart.setHoleRadius(40f);
        pieChart.setTransparentCircleAlpha(40);
        pieChart.setCenterText("My Expenses");
        pieChart.setCenterTextSize(15);
        pieChart.setCenterTextColor(Color.WHITE);


        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value Selected from chart");
                Log.d(TAG, "onValueSelected: "+ e.toString());
                Log.d(TAG, "onValueSelected: "+ h.toString());

                int pos1 = e.toString().indexOf("y: ");
                String total = e.toString().substring(pos1 + 3);
                for (int i=0;i<yData.length;i++){
                    if (yData[i] == Float.parseFloat(total)){
                        pos1 = i;
                        break;
                    }
                }
                String categries = xData[pos1];
                tvPieChart.setText("Categry: "+categries + "\n"+"Total: $" +total +" ");
            }

            @Override
            public void onNothingSelected() {

            }
        });


        return v;
    }

    private void addDataSet(){
        Log.d(TAG, "addDataSet: started");
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        ArrayList<String> xEntry = new ArrayList<>();

        for(int i=0; i<yData.length;i++){
            yEntry.add(new PieEntry(yData[i] , i));
        }

        for(int i=0; i<xData.length;i++){
            xEntry.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet( yEntry, "Money Spent");
        pieDataSet.setSliceSpace(0);
        pieDataSet.setValueTextSize(14);
        pieDataSet.setValueTextColor(Color.rgb(255,255,255));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(255,243,95));
        colors.add(Color.rgb(116,169,187));
        colors.add(Color.rgb(4,135,166));
        colors.add(Color.rgb(88,46,96));
        colors.add(Color.rgb(229,44,101));
        colors.add(Color.rgb(238,83,79));
        colors.add(Color.rgb(255,200,81));


        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data objects
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();



    }

    private int sumArray(String[] string) {
        int sum = 0;

        for (int i = 0; i < string.length; i++) {
            sum = sum + Integer.parseInt(string[i]);
        }
        sum = -sum;
        return sum;
    }


}


