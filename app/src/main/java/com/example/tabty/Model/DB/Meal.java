package com.example.tabty.Model.DB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;


public class Meal implements Serializable {
    public String idMeal;
    public String strMeal;
    public Object strDrinkAlternate;
    public String strCategory;
    public String strArea;
    public String strInstructions;
    public String strMealThumb;
    public String strTags;
    public String strYoutube;
    public String strIngredient1;
    public String strIngredient2;
    public String strIngredient3;
    public String strIngredient4;
    public String strIngredient5;
    public String strIngredient6;
    public String strIngredient7;
    public String strIngredient8;
    public String strIngredient9;
    public String strIngredient10;
    public String strIngredient11;
    public String strIngredient12;
    public String strIngredient13;
    public String strIngredient14;
    public String strIngredient15;
    public String strIngredient16;
    public String strIngredient17;
    public String strIngredient18;
    public String strIngredient19;
    public String strIngredient20;
    public String strMeasure1;
    public String strMeasure2;
    public String strMeasure3;
    public String strMeasure4;
    public String strMeasure5;
    public String strMeasure6;
    public String strMeasure7;
    public String strMeasure8;
    public String strMeasure9;
    public String strMeasure10;
    public String strMeasure11;
    public String strMeasure12;
    public String strMeasure13;
    public String strMeasure14;
    public String strMeasure15;
    public String strMeasure16;
    public String strMeasure17;
    public String strMeasure18;
    public String strMeasure19;
    public String strMeasure20;
    public String strSource;
    public Object strImageSource;
    public Object strCreativeCommonsConfirmed;
    public Object dateModified;
    public ArrayList<String> ingredients;
    public ArrayList<String> measures;
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
