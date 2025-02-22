package com.example.tabty.Model.Network.POJOs;

public class Ingredient {
        public String idIngredient;
        public String strIngredient;
        public String strDescription;

    public Ingredient() {
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient='" + idIngredient + '\'' +
                ", strIngredient='" + strIngredient + '\'' +
                ", strDescription='" + strDescription + '\'' +
                '}';
    }
}
