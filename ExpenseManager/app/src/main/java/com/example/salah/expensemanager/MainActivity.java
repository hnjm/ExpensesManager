package com.example.salah.expensemanager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    BottomBar aBottomBar;
    DBconnection database;
    BroadcastReceiver receiver = null;
    private static String TAG = "Statistics";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arr0, Intent arr1) {
                processReceive(arr0,arr1);
            }
        };
        registerReceiver(receiver,filter);

        database = new DBconnection(this);



        // Customizing the color here
        //background color, Tab item color, tab alpha
        aBottomBar = BottomBar.attach(this,savedInstanceState);
        //getting my menu item
        aBottomBar.setItems(R.menu.menu_main);

        aBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener(){
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.BottomHome){
                    HomeFragment f = new HomeFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if (menuItemId == R.id.BottomStatistics){
                    StatisticsFragment f = new StatisticsFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if (menuItemId == R.id.BottomProfile){
                    ProfileFragment f = new ProfileFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if (menuItemId == R.id.Bottomsettings){
                    SettingsFragment f = new SettingsFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        aBottomBar.mapColorForTab(0,"#4357AD");
        aBottomBar.mapColorForTab(1,"#48A9A6");
        aBottomBar.mapColorForTab(2,"#D4B483");
        aBottomBar.mapColorForTab(3,"#C1666B");


        BottomBarBadge unreed;
        unreed = aBottomBar.makeBadgeForTabAt(0,"#FF0000",1);
        unreed.show();

    }

    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void processReceive(Context context, Intent intent){
        Bundle bundle = intent.getExtras();
        Object[] objArr = (Object[])bundle.get("pdus");
        String sms="";
        for (int i=0;i<objArr.length;i++){
            SmsMessage smsMsg = SmsMessage.createFromPdu((byte[])objArr[i]);
            String smsBody = smsMsg.getMessageBody();
            String sendNumber = smsMsg.getDisplayOriginatingAddress();
            sms = "From "+sendNumber+"\n Content " +smsBody+"\n";

        }
        Log.d(TAG, "processReceive: "+sms.toString());
        Toast.makeText(context,"recived",Toast.LENGTH_SHORT);
    }

}
