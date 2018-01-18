package com.udacity.willbrom.bakingapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ybq.android.spinkit.SpinKitView;
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


public class MasterListFragment extends Fragment implements RecipeListAdapter.ItemClickListener, LoaderManager.LoaderCallbacks<String> {

    private static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final String RECIPE_URL_EXTRA = "recipe";
    private static final int RECIPE_LOADER = 11;
    private List<RecipeModel> recipeModel;
    private final Type recipeListType = new TypeToken<ArrayList<RecipeModel>>(){}.getType();
    @BindView(R.id.recipe_list) RecyclerView recipeList;
    @BindView(R.id.spin_kit) SpinKitView spinKitView;
    private RecipeListAdapter recipeListAdapter;
    private RecipeListener recipeClickListener;

    public interface RecipeListener {
        void onRecipeClicked(RecipeModel recipeModel);
        void showErrorSnackBar();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            recipeClickListener = (RecipeListener) context;
        } catch (ClassCastException ec) {
            throw new ClassCastException(context.toString()
                    + " must implement RecipeListener");
        }
    }

    public MasterListFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        ButterKnife.bind(this, rootView);
        recipeListAdapter = new RecipeListAdapter(this);
        recipeList.setNestedScrollingEnabled(false);

        if (rootView.findViewById(R.id.check_view) != null)
            recipeList.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2, GridLayoutManager.VERTICAL, false));
        else
            recipeList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));

        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(recipeListAdapter);

        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        if (savedInstanceState == null) {
            Bundle recipeBundle = new Bundle();
            recipeBundle.putString(RECIPE_URL_EXTRA, RECIPE_URL);
            Loader<String> recipeLoader = loaderManager.getLoader(RECIPE_LOADER);

            if (recipeLoader == null)
                loaderManager.initLoader(RECIPE_LOADER, recipeBundle, this);
            else
                loaderManager.restartLoader(RECIPE_LOADER, recipeBundle, this);

        } else {
            loaderManager.initLoader(RECIPE_LOADER, null, this);
        }

        return rootView;
    }

    @Override
    public void onClick(RecipeModel recipeModel) {
        recipeClickListener.onRecipeClicked(recipeModel);
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(getContext()) {
            String mRecipeJson;

            @Override
            protected void onStartLoading() {
                if (mRecipeJson != null)
                    deliverResult(mRecipeJson);
                else {
                    spinKitView.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Override
            public String loadInBackground() {
                String recipeUrlString = args.getString(RECIPE_URL_EXTRA);
                return NetworkUtils.getHttpResponse(recipeUrlString);
            }

            @Override
            public void deliverResult(String data) {
                mRecipeJson = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        spinKitView.setVisibility(View.INVISIBLE);
        if (data != null) {
            recipeModel = new Gson().fromJson(data, recipeListType);
            recipeListAdapter.setRecipeModelList(recipeModel);
        } else {
            recipeClickListener.showErrorSnackBar();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
//        not using this
    }
}
