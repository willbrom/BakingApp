package com.udacity.willbrom.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.willbrom.bakingapp.fragments.StepDetailFragment;
import com.udacity.willbrom.bakingapp.model.StepsModel;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(Intent.EXTRA_TEXT);
            StepsModel stepsModel = (StepsModel) bundle.getSerializable("ser");

            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            stepDetailFragment.setStepsModel(stepsModel);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, stepDetailFragment)
                    .commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }

}
