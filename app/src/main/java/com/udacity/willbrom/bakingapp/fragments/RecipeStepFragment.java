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
import com.udacity.willbrom.bakingapp.adapter.StepListAdapter;
import com.udacity.willbrom.bakingapp.model.StepsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeStepFragment extends Fragment {

    private List<StepsModel> stepsModelList;
    @BindView(R.id.step_list) RecyclerView stepList;
    private Unbinder unbinder;


    public RecipeStepFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        StepListAdapter stepListAdapter = new StepListAdapter();
        stepListAdapter.setStepsModelList(stepsModelList);
        stepList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        stepList.setAdapter(stepListAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setStepsModelList(List<StepsModel> stepsModelList) {
        this.stepsModelList = stepsModelList;
    }
}
