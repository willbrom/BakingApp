package com.udacity.willbrom.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.willbrom.bakingapp.fragments.MasterListFragment;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.master_list_container, new MasterListFragment())
                .commit();
    }
}

