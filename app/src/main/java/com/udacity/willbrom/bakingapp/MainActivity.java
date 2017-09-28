package com.udacity.willbrom.bakingapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.willbrom.bakingapp.model.RecipeModel;
import com.udacity.willbrom.bakingapp.utilities.NetworkUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final int LOADER_NUM = 11;
    private TextView results;

//    used by Gson to map Json array to List of type RecipeModel
    private List<RecipeModel> recipeModel;
    private final Type recipeListType = new TypeToken<ArrayList<RecipeModel>>(){}.getType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = (TextView) findViewById(R.id.tv_results);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        getSupportLoaderManager().initLoader(LOADER_NUM, null, this);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            String previousData;

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                Log.d(TAG, "onStartLoading");
                if (previousData != null) {
                    deliverResult(previousData);
                }
                else {
                    forceLoad();
                }
            }

            @Override
            public String loadInBackground() {
                Log.d(TAG, "loadInBackground called");
                String returnedJson = NetworkUtils.getHttpResponse(RECIPE_URL);
                Log.d(TAG, returnedJson);
                return returnedJson;
            }

            @Override
            public void deliverResult(String data) {
                Log.d(TAG, "deliverResult got called");
                previousData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null) {
//            Gson at work here, mapping Json to List of type RecipeModel
            recipeModel = new Gson().fromJson(data, recipeListType);
            for (int i = 0; i < recipeModel.size(); i++ ) {
                results.append(recipeModel.get(i).getName() + "\n\n\n");
            }
        }
        else
            Log.d(TAG, "no data returned");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
