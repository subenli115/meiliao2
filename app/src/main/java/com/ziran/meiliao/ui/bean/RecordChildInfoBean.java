package com.ziran.meiliao.ui.bean;

/**
 * 子recycler on 2018/8/16.
 */

public class RecordChildInfoBean {

    /**
     * id : 1
     * days : 1
     * status : 0
     * name : 身体扫描
     * type_id : 1
     * books_id : 1
     */

    private int id;
    private int days;
    private int status;
    private String name;
    private int type_id;
    private int books_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getBooks_id() {
        return books_id;
    }

    public void setBooks_id(int books_id) {
        this.books_id = books_id;
    }
}
