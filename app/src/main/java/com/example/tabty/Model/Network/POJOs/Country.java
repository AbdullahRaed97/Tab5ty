package com.example.tabty.Model.Network.POJOs;

public class Country {
    public String strArea;

    public Country() {
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    @Override
    public String toString() {
        return "Country{" +
                "strArea='" + strArea + '\'' +
                '}';
    }
}
