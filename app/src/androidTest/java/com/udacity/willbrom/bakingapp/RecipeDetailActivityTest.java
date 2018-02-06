package com.udacity.willbrom.bakingapp;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest  {

    private static final String NUTELLA_PIE = "Nutella Pie";
    private static final String RECIPES = "Recipe's";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recipeListTitle_Check() {
        onView(withId(R.id.recipe_list_title)).check(matches(withText(RECIPES)));
    }

    @Test
    public void clickRecyclerViewItem_NutellaPie_OpensRecipeDetailActivity() {
        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.title_text_view)).check(matches(withText(NUTELLA_PIE)));
    }

}
