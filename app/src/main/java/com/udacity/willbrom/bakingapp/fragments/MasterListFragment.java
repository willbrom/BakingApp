package com.udacity.willbrom.bakingapp.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.models.RecipeModel;
import com.udacity.willbrom.bakingapp.utilities.NetworkUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MasterListFragment extends Fragment {

    private static final String TAG = MasterListFragment.class.getSimpleName();
    private static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private List<RecipeModel> recipeModel;
    private final Type recipeListType = new TypeToken<ArrayList<RecipeModel>>(){}.getType();
    @BindView(R.id.tv_frag) TextView tv;

    public MasterListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called!!");
        new PerformNetworkTask().execute();
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public class PerformNetworkTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground called!!");
            String returnedJson = NetworkUtils.getHttpResponse(RECIPE_URL);
            return returnedJson;

        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute called!!");
            recipeModel = new Gson().fromJson(s, recipeListType);
            Log.d(TAG, recipeModel.get(0).getName());
            tv.setText(recipeModel.get(0).getName());
        }

    }

}
