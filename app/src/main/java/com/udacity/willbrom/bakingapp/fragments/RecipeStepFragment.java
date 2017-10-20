package com.udacity.willbrom.bakingapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.adapter.StepListAdapter;
import com.udacity.willbrom.bakingapp.model.StepsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecipeStepFragment extends Fragment implements StepListAdapter.StepItemClickListener {

    private static final String TAG = RecipeStepFragment.class.getSimpleName();
    private List<StepsModel> stepsModelList;
    @BindView(R.id.step_list) RecyclerView stepList;
    private Unbinder unbinder;
    private OnStepItemClickListener clickListener;

    public interface OnStepItemClickListener {
        void onStepItemClicked(StepsModel stepsModel);
    }

    public RecipeStepFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            clickListener = (OnStepItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepItemClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        StepListAdapter stepListAdapter = new StepListAdapter(this);
        stepListAdapter.setStepsModelList(stepsModelList);
        stepList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        stepList.setAdapter(stepListAdapter);
        stepList.setNestedScrollingEnabled(false);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClickStep(StepsModel stepsModel) {
        Log.d(TAG, stepsModel.getShortDescription());
        clickListener.onStepItemClicked(stepsModel);
    }

    public void setStepsModelList(List<StepsModel> stepsModelList) {
        this.stepsModelList = stepsModelList;
    }
}
