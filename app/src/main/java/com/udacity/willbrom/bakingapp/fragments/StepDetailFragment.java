package com.udacity.willbrom.bakingapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.model.StepsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StepDetailFragment extends Fragment {

    private StepsModel stepsModel;
    @BindView(R.id.step_long_description) TextView description;
    private Unbinder unbinder;

    public StepDetailFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        if (savedInstanceState != null) {
            stepsModel = (StepsModel) savedInstanceState.getSerializable("ser");
            description.setText(stepsModel.getDescription());
        } else {
            description.setText(stepsModel.getDescription());
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("ser", stepsModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setStepsModel(StepsModel stepsModel) {
        this.stepsModel = stepsModel;
    }
}
