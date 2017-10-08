package com.udacity.willbrom.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.udacity.willbrom.bakingapp.model.RecipeModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {

    @BindView (R.id.recipe_name)
    TextView recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        RecipeModel recipeModel = (RecipeModel) bundle.getSerializable("key");
        for (int i = 0; i < recipeModel.getIngredients().size(); i++) {
            recipeName.append(recipeModel.getIngredients().get(i).getIngredient() + "\n\n");
        }
    }
}
