package com.udacity.willbrom.bakingapp.model;


import java.util.List;

/**
 * class used by Gson library
 * to map 'root JSON array'
* */

public class RecipeModel {

    private int id;
    private String name;
    private List<IngredientsModel> ingredients;
    private List<StepsModel> steps;
    private int servings;
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<IngredientsModel> getIngredients() {
        return ingredients;
    }

    public List<StepsModel> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
