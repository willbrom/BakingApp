package com.udacity.willbrom.bakingapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.model.StepsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder> {

    private List<StepsModel> stepsModelList;

    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.step_item_layout, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        holder.stepDescription.setText(stepsModelList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (stepsModelList == null)return 0;
        return stepsModelList.size();
    }

    public void setStepsModelList(List<StepsModel> stepsModelList) {
        this.stepsModelList = stepsModelList;
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_description) TextView stepDescription;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
