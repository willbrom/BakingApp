package com.udacity.willbrom.bakingapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.udacity.willbrom.bakingapp.fragments.IngredientDetailFragment;
import com.udacity.willbrom.bakingapp.fragments.RecipeIngredientFragment;
import com.udacity.willbrom.bakingapp.fragments.RecipeStepFragment;
import com.udacity.willbrom.bakingapp.fragments.StepDetailFragment;
import com.udacity.willbrom.bakingapp.model.IngredientsModel;
import com.udacity.willbrom.bakingapp.model.RecipeModel;
import com.udacity.willbrom.bakingapp.model.StepsModel;
import com.udacity.willbrom.bakingapp.widget.IngredientListService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity
        implements RecipeIngredientFragment.OnIngredientItemClickListener,
        RecipeStepFragment.OnStepItemClickListener {

    private RecipeModel recipeModel;
    private boolean mTwoPane;
    private boolean mIngredientSelected = true;
    private StepsModel stepsModelSave;
    private static final String TAG = RecipeDetailActivity.class.getSimpleName();
    @BindView(R.id.title_text_view) TextView titleTextView;
    public static String recipeTitle = "Recipe Title";
    public static List<IngredientsModel> ingredientsModelList = new ArrayList<IngredientsModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        recipeModel = (RecipeModel) bundle.getSerializable("key");
        mTwoPane = false;
        titleTextView.setText(recipeModel.getName());

        FragmentManager fragmentManager = getSupportFragmentManager();

        RecipeIngredientFragment ingredientFragment = new RecipeIngredientFragment();
        ingredientFragment.setIngredientsModelList(recipeModel.getIngredients());

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.recipe_ingredient_container, ingredientFragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_ingredient_container, ingredientFragment)
                    .commit();
        }
        RecipeStepFragment stepFragment = new RecipeStepFragment();
        stepFragment.setStepsModelList(recipeModel.getSteps());

        fragmentManager.beginTransaction()
                .add(R.id.recipe_step_container, stepFragment)
                .commit();

        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true;
            IngredientDetailFragment ingredientDetailFragment = new IngredientDetailFragment();
            ingredientDetailFragment.setIngredientsModelList(recipeModel.getIngredients());
            if (savedInstanceState == null) {
                fragmentManager.beginTransaction()
                        .add(R.id.detail_container, ingredientDetailFragment)
                        .commit();
            } else {
                Log.d(TAG, "device rotated");
                Bundle bundle1 = savedInstanceState.getBundle("bun");
                mIngredientSelected = bundle1.getBoolean("bol");
                Log.d(TAG, mIngredientSelected + "");
                if (mIngredientSelected) {
                    Log.d(TAG, "Ingredient selected");
                    fragmentManager.beginTransaction()
                            .replace(R.id.detail_container, ingredientDetailFragment)
                            .commit();
                } else {
                    Log.d(TAG, "Steps selected");
                    StepsModel stepsModel = (StepsModel) bundle1.getSerializable("ser");
                    stepsModelSave = stepsModel;
                    Log.d(TAG, stepsModel.getVideoURL());
                    StepDetailFragment stepDetailFragment = new StepDetailFragment();
                    stepDetailFragment.setStepsModel(stepsModel);
                    fragmentManager.beginTransaction()
                            .replace(R.id.detail_container, stepDetailFragment)
                            .commit();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ser", stepsModelSave);
        bundle.putBoolean("bol", mIngredientSelected);
        outState.putBundle("bun", bundle);
    }

    @Override
    public void onIngredientItemClicked(List<IngredientsModel> ingredientsModelList) {
        if (mTwoPane) {
            mIngredientSelected = true;
            IngredientDetailFragment ingredientDetailFragment = new IngredientDetailFragment();
            ingredientDetailFragment.setIngredientsModelList(ingredientsModelList);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, ingredientDetailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, IngredientDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("key", (Serializable) ingredientsModelList);
            intent.putExtra(Intent.EXTRA_TEXT, bundle);
            startActivity(intent);
        }
    }

    @Override
    public void onStepItemClicked(StepsModel stepsModel) {
        if (mTwoPane) {
            stepsModelSave = stepsModel;
            mIngredientSelected = false;
            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            stepDetailFragment.setStepsModel(stepsModel);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, stepDetailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, StepDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ser", stepsModel);
            intent.putExtra(Intent.EXTRA_TEXT, bundle);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_recipe_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_add) {
            recipeTitle = recipeModel.getName();
            ingredientsModelList = recipeModel.getIngredients();
            IngredientListService.startActionChangeIngredientList(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
