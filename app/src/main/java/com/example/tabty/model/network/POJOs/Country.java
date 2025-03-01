package com.example.tabty.model.network.POJOs;

public class Country {
    private String strArea;

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
