package com.udacity.willbrom.bakingapp.adapter;


import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.willbrom.bakingapp.R;
import com.udacity.willbrom.bakingapp.model.RecipeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder> {

    private List<RecipeModel> recipeModelList;
    private final ItemClickListener itemClickListener;
    private static final String TAG = RecipeListAdapter.class.getSimpleName();

    public interface ItemClickListener {
        void onClick(RecipeModel recipeModel);
    }

    public RecipeListAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder got called!!");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Context context = holder.recipeThumbnail.getContext();
        String imagePath = recipeModelList.get(position).getImage();

        if (imagePath.equals("")) {
            imagePath = "http://image.tmdb.org";
        }

        holder.recipeTitle.setText(recipeModelList.get(position).getName());
        Picasso.with(context)
                .load(imagePath)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(holder.recipeThumbnail);
    }

    @Override
    public int getItemCount() {
        if (recipeModelList == null) return 0;
        return recipeModelList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recipe_thumbnail) ImageView recipeThumbnail;
        @BindView(R.id.recipe_title) TextView recipeTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ItemViewHolder got called!");
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            itemClickListener.onClick(recipeModelList.get(position));
        }

    }

    public void setRecipeModelList(List<RecipeModel> recipeModelList) {
        this.recipeModelList = recipeModelList;
        notifyDataSetChanged();
    }
}
