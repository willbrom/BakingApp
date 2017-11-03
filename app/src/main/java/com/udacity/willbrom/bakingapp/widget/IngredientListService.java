package com.udacity.willbrom.bakingapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.udacity.willbrom.bakingapp.RecipeDetailActivity;


public class IngredientListService extends IntentService {

    public static final String ACTION_CHANGE_INGREDIENT_LIST = "com.udacity.willbrom.bakingapp.widget.action.change_list";

    public IngredientListService() {
        super("IngredientListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CHANGE_INGREDIENT_LIST.equals(action)) {
                handleActionChangeIngredientList();
            }
        }
    }

    public static void startActionChangeIngredientList(Context context) {
        Intent intent = new Intent(context, IngredientListService.class);
        intent.setAction(ACTION_CHANGE_INGREDIENT_LIST);
        context.startService(intent);
    }

    private void handleActionChangeIngredientList() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeIngredientWidgetProvider.class));
        RecipeIngredientWidgetProvider.updateIngredientWidgets(this, appWidgetManager, RecipeDetailActivity.recipeTitle, appWidgetIds);
    }

}
