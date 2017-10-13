package com.udacity.willbrom.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.udacity.willbrom.bakingapp.fragments.IngredientDetailFragment;
import com.udacity.willbrom.bakingapp.fragments.MasterListFragment;
import com.udacity.willbrom.bakingapp.fragments.RecipeIngredientFragment;
import com.udacity.willbrom.bakingapp.model.IngredientsModel;

import java.util.List;

public class IngredientDetailActivity extends AppCompatActivity {

    private List<IngredientsModel> ingredientsModelList;
    private static final String TAG = IngredientDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Intent.EXTRA_TEXT);
        ingredientsModelList = (List<IngredientsModel>) bundle.getSerializable("key");

        IngredientDetailFragment detailFragment = new IngredientDetailFragment();
        detailFragment.setIngredientsModelList(ingredientsModelList);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.ingredient_detail_container, detailFragment)
                .commit();
    }

}
