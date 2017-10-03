package com.udacity.willbrom.bakingapp.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.adapter.RecipeListAdapter;
import com.udacity.willbrom.bakingapp.model.RecipeModel;
import com.udacity.willbrom.bakingapp.utilitie.NetworkUtils;

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
    @BindView(R.id.recipe_list)
    RecyclerView recipeList;
    private RecipeListAdapter recipeListAdapter;

    public MasterListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called!!");
        new PerformNetworkTask().execute();
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        ButterKnife.bind(this, rootView);
        recipeListAdapter = new RecipeListAdapter();
        recipeList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(recipeListAdapter);
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
            if (s != null) {
                recipeModel = new Gson().fromJson(s, recipeListType);
                Log.d(TAG, recipeModel.get(0).getName());
                recipeListAdapter.setRecipeModel(recipeModel);
            }

        }

    }

}
