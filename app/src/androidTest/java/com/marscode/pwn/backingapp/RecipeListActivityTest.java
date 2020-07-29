package com.marscode.pwn.backingapp;

import android.util.Log;

import com.marscode.pwn.backingapp.screens.IdlingBuilder;
import com.marscode.pwn.backingapp.screens.recipelist.RecipesListActivity;
import com.marscode.pwn.backingapp.screens.recipelist.RecipesListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListActivityTest {
    @Rule
    public ActivityTestRule<RecipesListActivity> recipesListActivityTestRule = new ActivityTestRule<>(RecipesListActivity.class);

    private IdlingResource idlingResource;
    private IdlingBuilder mIdlingBuilder;

    @Before
    public void registerIdlingResource() {

        mIdlingBuilder = RecipesListFragment.getInstance().setupIdlingBuilder();
        idlingResource = mIdlingBuilder.getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void clickOnRecyclerViewItem_opensRecipeDetailsActivity() {

        onView(ViewMatchers.withId(R.id.recipes_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.card_ingredient)).check(matches(isDisplayed()));
    }


    @After
    public void unRegisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }


}
