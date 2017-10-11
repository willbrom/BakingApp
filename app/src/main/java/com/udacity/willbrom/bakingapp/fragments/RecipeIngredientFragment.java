package com.udacity.willbrom.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.adapter.IngredientListAdapter;
import com.udacity.willbrom.bakingapp.model.IngredientsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecipeIngredientFragment extends Fragment {

    private List<IngredientsModel> ingredientsModelList;
    @BindView(R.id.ingredient_list) RecyclerView ingredientList;
    private Unbinder unbinder;

    public RecipeIngredientFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_ingredient, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        IngredientListAdapter ingredientListAdapter = new IngredientListAdapter();
        ingredientListAdapter.setIngredientsModelList(ingredientsModelList);
        ingredientList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        ingredientList.setAdapter(ingredientListAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setIngredientsModelList(List<IngredientsModel> ingredientsModelList) {
        this.ingredientsModelList = ingredientsModelList;
    }
}
