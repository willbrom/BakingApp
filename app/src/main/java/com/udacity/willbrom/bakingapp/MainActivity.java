package com.udacity.willbrom.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.udacity.willbrom.bakingapp.fragments.MasterListFragment;
import com.udacity.willbrom.bakingapp.model.RecipeModel;


public class MainActivity extends AppCompatActivity implements MasterListFragment.OnRecipeClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.master_list_container, new MasterListFragment())
                    .commit();
        } else {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.master_list_container, new MasterListFragment())
//                    .commit();
        }
    }

    @Override
    public void onRecipeClicked(RecipeModel recipeModel) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", recipeModel);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

