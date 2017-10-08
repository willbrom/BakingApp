package com.udacity.willbrom.bakingapp.model;


import java.io.Serializable;

/**
 * class used by Gson library
 * to map 'ingredient JSON array'
 * */

public class IngredientsModel implements Serializable {

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
