package com.udacity.willbrom.bakingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.willbrom.bakingapp.IngredientDetailActivity;
import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.adapter.IngredientListAdapter;
import com.udacity.willbrom.bakingapp.model.IngredientsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class RecipeIngredientFragment extends Fragment {

    private List<IngredientsModel> ingredientsModelList;
    private OnIngredientItemClickListener clickListener;

    public interface OnIngredientItemClickListener {
        void onItemClicked(List<IngredientsModel> ingredientsModelList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            clickListener = (OnIngredientItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIngredientItemClickListener");
        }
    }

    public RecipeIngredientFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_ingredient, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.card_ingredient)
    void onClick() {
        clickListener.onItemClicked(ingredientsModelList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setIngredientsModelList(List<IngredientsModel> ingredientsModelList) {
        this.ingredientsModelList = ingredientsModelList;
    }
}
