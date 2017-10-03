package com.udacity.willbrom.bakingapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.model.RecipeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder> {

    private List<RecipeModel> recipeModel;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.recipeItem.setText(recipeModel.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (recipeModel == null) return 0;
        return recipeModel.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipe_item)
        TextView recipeItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setRecipeModel(List<RecipeModel> recipeModel) {
        this.recipeModel = recipeModel;
        notifyDataSetChanged();
    }
}
