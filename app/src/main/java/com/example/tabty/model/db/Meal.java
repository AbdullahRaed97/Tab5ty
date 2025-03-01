package com.example.tabty.model.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;


public class Meal  {
    private String idMeal;
    private String strMeal;
    private Object strDrinkAlternate;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;
    private String strSource;
    private Object strImageSource;
    private Object strCreativeCommonsConfirmed;
    private Object dateModified;
    private ArrayList<String> ingredients;
    private ArrayList<String> measures;
    public Meal()
    {

    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public Object getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(Object strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public Object getStrImageSource() {
        return strImageSource;
    }

    public void setStrImageSource(Object strImageSource) {
        this.strImageSource = strImageSource;
    }

    public Object getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public void setStrCreativeCommonsConfirmed(Object strCreativeCommonsConfirmed) {
        this.strCreativeCommonsConfirmed = strCreativeCommonsConfirmed;
    }

    public ArrayList<String> getIngredients() {
        if(ingredients==null) {
            ingredients = new ArrayList<>();
            ingredients.add(strIngredient1);
            ingredients.add(strIngredient2);
            ingredients.add(strIngredient3);
            ingredients.add(strIngredient4);
            ingredients.add(strIngredient5);
            ingredients.add(strIngredient6);
            ingredients.add(strIngredient7);
            ingredients.add(strIngredient8);
            ingredients.add(strIngredient9);
            ingredients.add(strIngredient10);
            ingredients.add(strIngredient11);
            ingredients.add(strIngredient12);
            ingredients.add(strIngredient13);
            ingredients.add(strIngredient14);
            ingredients.add(strIngredient15);
            ingredients.add(strIngredient16);
            ingredients.add(strIngredient17);
            ingredients.add(strIngredient18);
            ingredients.add(strIngredient19);
            ingredients.add(strIngredient20);
            ingredients.removeIf(i->i==null || i.isEmpty());
        }
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getMeasures() {
        if(measures==null) {
            measures=new ArrayList<>();
            measures.add(strMeasure1);
            measures.add(strMeasure2);
            measures.add(strMeasure3);
            measures.add(strMeasure4);
            measures.add(strMeasure5);
            measures.add(strMeasure6);
            measures.add(strMeasure7);
            measures.add(strMeasure8);
            measures.add(strMeasure9);
            measures.add(strMeasure10);
            measures.add(strMeasure11);
            measures.add(strMeasure12);
            measures.add(strMeasure13);
            measures.add(strMeasure14);
            measures.add(strMeasure15);
            measures.add(strMeasure16);
            measures.add(strMeasure17);
            measures.add(strMeasure18);
            measures.add(strMeasure19);
            measures.add(strMeasure20);
            measures.removeIf(new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return (s==null || s.isEmpty());
                }
            });
        }
        return measures;
    }

    public void setMeasures(ArrayList<String> measures) {

        this.measures = measures;
    }

    public Object getDateModified() {
        return dateModified;
    }

    public void setDateModified(Object dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "idMeal='" + idMeal + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strDrinkAlternate=" + strDrinkAlternate +
                ", strCategory='" + strCategory + '\'' +
                ", strArea='" + strArea + '\'' +
                ", strInstructions='" + strInstructions + '\'' +
                ", strMealThumb='" + strMealThumb + '\'' +
                ", strTags='" + strTags + '\'' +
                ", strYoutube='" + strYoutube + '\'' +
                ", strSource='" + strSource + '\'' +
                ", strImageSource=" + strImageSource +
                ", strCreativeCommonsConfirmed=" + strCreativeCommonsConfirmed +
                ", dateModified=" + dateModified +
                ", ingredients=" + ingredients +
                ", measures=" + measures +
                '}';
    }
}
