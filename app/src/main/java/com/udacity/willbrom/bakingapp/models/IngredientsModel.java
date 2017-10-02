package com.udacity.willbrom.bakingapp.models;


/**
 * class used by Gson library
 * to map 'ingredient JSON array'
 * */

public class IngredientsModel {

    private float quantity;
    private String measure;
    private String ingredient;

    public float getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
