package com.example.salah.expensemanager;

/**
 * Created by Salah on 06-Jan-17.
 */

public class Expenses {

    private int id;
    private int money;
    private String category;
    private String date;

    public Expenses(int id, int money, String category, String date) {
        this.id = id;
        this.money = money;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
