package com.example.salah.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class DBconnection {


    DBinfo dBinfo;
    public DBconnection(Context context){
        dBinfo = new DBinfo(context);
    }

    public long dataInsert(int Money , String Category, String Date){

        SQLiteDatabase sqLiteDatabase = dBinfo.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBinfo.money,Money);
        contentValues.put(DBinfo.category,Category);
        contentValues.put(DBinfo.date,Date);
        long id = sqLiteDatabase.insert(DBinfo.tableName,null,contentValues);
        return id;
    }

    public List<Expenses> viewMoney(){
        Expenses expenses = null;
        List<Expenses> expensesList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dBinfo.getWritableDatabase();

        String [] columns = {DBinfo.UID,DBinfo.money,DBinfo.category,DBinfo.date};

        Cursor cursor = sqLiteDatabase.query(DBinfo.tableName,columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            int index0 = cursor.getColumnIndex(DBinfo.UID);
            int index1 = cursor.getColumnIndex(DBinfo.money);
            int index2 = cursor.getColumnIndex(DBinfo.category);
            int index3 = cursor.getColumnIndex(DBinfo.date);

            int Id = cursor.getInt(index0);
            int Moneye = cursor.getInt(index1);
            String Category = cursor.getString(index2);
            String date = cursor.getString(index3);
            expenses = new Expenses(Id,Moneye,Category,date);
            expensesList.add(expenses);
        }
        return expensesList;

    }

    public ArrayList<String> searchCategory(String cate){
        SQLiteDatabase sqLiteDatabase = dBinfo.getWritableDatabase();

        String [] columns = {DBinfo.money,DBinfo.category,DBinfo.date};

        Cursor cursor = sqLiteDatabase.query(DBinfo.tableName,columns,DBinfo.category+" = '"+cate+"'",null,null,null,null);

        ArrayList<String> arrayList = new ArrayList<>();

        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DBinfo.money);
            String Moneye = cursor.getString(index);
            arrayList.add(Moneye);
        }
        return arrayList;

    }

    public ArrayList<String> getMinus (){
        SQLiteDatabase sqLiteDatabase = dBinfo.getWritableDatabase();

        String [] columns = {DBinfo.money,DBinfo.category,DBinfo.date};

        Cursor cursor = sqLiteDatabase.query(DBinfo.tableName,columns,DBinfo.money+" LIKE '-%'",null,null,null,null);

        ArrayList<String> arrayList = new ArrayList<>();

        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DBinfo.money);
            String Moneye = cursor.getString(index);
            arrayList.add(Moneye);
        }
        return arrayList;

    }

    public ArrayList<String> getPositive (){
        SQLiteDatabase sqLiteDatabase = dBinfo.getWritableDatabase();

        String [] columns = {DBinfo.money,DBinfo.category};

        Cursor cursor = sqLiteDatabase.query(DBinfo.tableName,columns,DBinfo.money+" NOT LIKE '-%'",null,null,null,null);

        ArrayList<String> arrayList = new ArrayList<>();

        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DBinfo.money);
            String Moneye = cursor.getString(index);
            arrayList.add(Moneye);
        }
        return arrayList;

    }

    static class DBinfo extends SQLiteOpenHelper{

        //declaring my variables
        private static final String dataBase_Name = "expenseManager";
        private static final String tableName = "IncomeOutcome";
        private static final int dataBase_Version = 3;
        private static final String UID = "id";
        private static final String money = "Money";
        private static final String category = "Category";
        private static final String date = "Date";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+tableName;
        private static final String CREATE_TABLE = "CREATE TABLE "+tableName+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+money+" INTEGER, "+category+" VARCHAR(255),"+date+" date);";
        private Context context;


        public DBinfo(Context context) {
            super(context, dataBase_Name, null, dataBase_Version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE);
            }catch (SQLException e){
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (SQLException e){
            }
        }
    }


}