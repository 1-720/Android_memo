package com.example.myapplication.model;

public class Memo {

    private int id;
    private String memo;
    private String dateMemoAdded;

    public Memo() {
    }

    public Memo(String memo) {
        this.memo = memo;
    }

    public Memo(String memo, String dateMemoAdded) {
        this.memo = memo;
        this.dateMemoAdded = dateMemoAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDateMemoAdded() {
        return dateMemoAdded;
    }

    public void setDateMemoAdded(String dateMemoAdded) {
        this.dateMemoAdded = dateMemoAdded;
    }
}
