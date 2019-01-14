package com.ch.spider.entity;

public class taskinfo {
    public String ColumnUrl="";
    public int typeID=0;//栏目的编号

    public String getColumnUrl() {
        return ColumnUrl;
    }

    public void setColumnUrl(String columnUrl) {
        ColumnUrl = columnUrl;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }
}
