package com.example.tabty.model.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "Meals")
public class MealEntity {
        @PrimaryKey
        @NonNull
        private String idMeal;
        private String strMeal;
        private String strCategory;
        private String strArea;
        private String strInstructions;
        private String strMealThumb;
        private String strTags;
        private String strYoutube;
        @Ignore
        private ArrayList<String> ingredients;
        @Ignore
        private ArrayList<String> measures;
        public MealEntity() {

        }
        public MealEntity(Meal meal){
            this.idMeal=meal.getIdMeal();
            this.ingredients=meal.getIngredients();
            this.strMeal=meal.getStrMeal();
            this.strArea=meal.getStrArea();
            this.measures=meal.getMeasures();
            this.strTags=meal.getStrTags();
            this.strYoutube=meal.getStrYoutube();
            this.strMealThumb=meal.getStrMealThumb();
            this.strCategory=meal.getStrCategory();
            this.strInstructions=meal.getStrInstructions();
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

        public ArrayList<String> getIngredients() {
            return ingredients;
        }

        public void setIngredients(ArrayList<String> ingredients) {
            this.ingredients = ingredients;
        }

        public ArrayList<String> getMeasures() {
            return measures;
        }
        public void setMeasures(ArrayList<String> measures) {
            this.measures = measures;
        }

        @Override
        public String toString() {
            return "Meal{" +
                    "idMeal='" + idMeal + '\'' +
                    ", strMeal='" + strMeal + '\'' +
                    ", strCategory='" + strCategory + '\'' +
                    ", strArea='" + strArea + '\'' +
                    ", strInstructions='" + strInstructions + '\'' +
                    ", strMealThumb='" + strMealThumb + '\'' +
                    ", strTags='" + strTags + '\'' +
                    ", strYoutube='" + strYoutube + '\'' +
                    ", ingredients=" + ingredients +
                    ", measures=" + measures +
                    '}';
        }

}
