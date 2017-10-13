package com.udacity.willbrom.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.adapter.IngredientListAdapter;
import com.udacity.willbrom.bakingapp.model.IngredientsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class IngredientDetailFragment extends Fragment {

    private List<IngredientsModel> ingredientsModelList;
    @BindView(R.id.ingredient_detail_list) RecyclerView ingredientList;
    private Unbinder unbinder;

    public IngredientDetailFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredient_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        ingredientList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        IngredientListAdapter adapter = new IngredientListAdapter();
        adapter.setIngredientsModelList(ingredientsModelList);
        ingredientList.setAdapter(adapter);
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
