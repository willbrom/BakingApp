package com.udacity.willbrom.bakingapp.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.RecipeDetailActivity;
import com.udacity.willbrom.bakingapp.adapter.RecipeListAdapter;
import com.udacity.willbrom.bakingapp.model.RecipeModel;
import com.udacity.willbrom.bakingapp.utilitie.NetworkUtils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MasterListFragment extends Fragment implements RecipeListAdapter.ItemClickListener {

    private static final String TAG = MasterListFragment.class.getSimpleName();
    private static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private List<RecipeModel> recipeModel;
    private final Type recipeListType = new TypeToken<ArrayList<RecipeModel>>(){}.getType();
    @BindView(R.id.recipe_list) RecyclerView recipeList;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.error_textView) TextView errorTextView;
    private Unbinder unbinder;
    private RecipeListAdapter recipeListAdapter;
    private OnRecipeClickListener recipeClickListener;
    private boolean mTwoPane;

    public interface OnRecipeClickListener{
        void onRecipeClicked(RecipeModel recipeModel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            recipeClickListener = (OnRecipeClickListener) context;
        } catch (ClassCastException ec) {
            throw new ClassCastException(context.toString()
                    + " must implement OnRecipeClickListener");
        }
    }

    public MasterListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called!!");

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        recipeListAdapter = new RecipeListAdapter(this);
        recipeList.setNestedScrollingEnabled(false);

        if (rootView.findViewById(R.id.check_view) != null) {
            mTwoPane = true;
            recipeList.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2, GridLayoutManager.VERTICAL, false));
        } else {
            mTwoPane = false;
            recipeList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        }

        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(recipeListAdapter);

        if (savedInstanceState != null) {
            recipeModel = (List<RecipeModel>) savedInstanceState.getSerializable("ser");

            if (recipeModel != null)
                Log.d(TAG, recipeModel.get(0).getName() + " " + recipeModel.get(1).getName() + " " + recipeModel.get(2).getName() + " " + recipeModel.get(3).getName());
            else
                Log.d(TAG, "recipeModel is Null");

            recipeListAdapter.setRecipeModelList(recipeModel);

//            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable("par");
//            recipeList.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        } else {
            new PerformNetworkTask().execute();
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("ser", (Serializable) recipeModel);
//        outState.putParcelable("par", recipeList.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(RecipeModel recipeModel) {
        Log.d(TAG, recipeModel.getName());
        recipeClickListener.onRecipeClicked(recipeModel);
    }

    public class PerformNetworkTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground called!!");
            String returnedJson = NetworkUtils.getHttpResponse(RECIPE_URL);
            return returnedJson;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute called!!");
            progressBar.setVisibility(View.INVISIBLE);
            if (s != null) {
                recipeModel = new Gson().fromJson(s, recipeListType);
                Log.d(TAG, recipeModel.get(0).getName());
                recipeListAdapter.setRecipeModelList(recipeModel);
            } else {
                errorTextView.setVisibility(View.VISIBLE);
            }
        }

    }

}
