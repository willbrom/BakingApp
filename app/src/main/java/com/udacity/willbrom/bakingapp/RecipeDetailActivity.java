package com.udacity.willbrom.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.willbrom.bakingapp.fragments.RecipeIngredientFragment;
import com.udacity.willbrom.bakingapp.fragments.RecipeStepFragment;
import com.udacity.willbrom.bakingapp.model.IngredientsModel;
import com.udacity.willbrom.bakingapp.model.RecipeModel;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity
        implements RecipeIngredientFragment.OnIngredientItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        RecipeModel recipeModel = (RecipeModel) bundle.getSerializable("key");

        FragmentManager fragmentManager = getSupportFragmentManager();
        RecipeIngredientFragment ingredientFragment = new RecipeIngredientFragment();
        ingredientFragment.setIngredientsModelList(recipeModel.getIngredients());

        fragmentManager.beginTransaction()
                .add(R.id.recipe_ingredient_container, ingredientFragment)
                .commit();

        RecipeStepFragment stepFragment = new RecipeStepFragment();
        stepFragment.setStepsModelList(recipeModel.getSteps());

        fragmentManager.beginTransaction()
                .add(R.id.recipe_step_container, stepFragment)
                .commit();
    }

    @Override
    public void onItemClicked(List<IngredientsModel> ingredientsModelList) {
        Intent intent = new Intent(this, IngredientDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", (Serializable) ingredientsModelList);
        intent.putExtra(Intent.EXTRA_TEXT, bundle);
        startActivity(intent);
    }
}
