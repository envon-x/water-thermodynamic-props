package com.iridiscense.unitoperations.plotter.data.psyco;

import android.graphics.drawable.Drawable;

import com.iridiscense.unitoperations.plotter.data.BaseEntry;

/**
 * This class was created for Comanda project
 * Created by bon on 6/2/21.
 * Copyright (c)  Hector Bonifacio. 6/2/21, All rights reserved.
 */
public class BaseData {

//
//    // ID of data
//    private int id;
//
    // Value of point
    private float x = 0.00f;

    // name of point belong collection
    private String collection;

    // Icon por the given point
    private Drawable icon;


    public BaseData() {

    }
    public BaseData(float x) {
        this.x = x;
    }

    public BaseData(float x, Drawable icon) {
        this.x = x;
        this.icon = icon;
    }


    public BaseData(float x, String collection, Drawable icon) {
        this.x = x;
        this.collection = collection;
        this.icon = icon;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
