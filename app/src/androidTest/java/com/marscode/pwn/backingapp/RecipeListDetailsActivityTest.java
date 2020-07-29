package com.marscode.pwn.backingapp;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;

import com.marscode.pwn.backingapp.screens.IdlingBuilder;
import com.marscode.pwn.backingapp.screens.recipeDetails.RecipeDetailsActivity;
import com.marscode.pwn.backingapp.screens.recipeDetails.RecipeDetailsFragment;
import com.marscode.pwn.backingapp.screens.recipeStepDetails.RecipeStepDetailsActivity;
import com.marscode.pwn.backingapp.screens.recipelist.RecipesListFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListDetailsActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> recipesListDetailsActivityTestRule = new ActivityTestRule<>(RecipeDetailsActivity.class, true, false);

    @Rule
    public IntentsTestRule<RecipeDetailsActivity> mActivityRule = new IntentsTestRule<>(RecipeDetailsActivity.class, true, false);

    private IdlingResource idlingResource;
    private IdlingBuilder mIdlingBuilder;
    private String EXPECTED_ID = "1";

    @Before
    public void registerIdlingResource() {
        mIdlingBuilder = RecipeDetailsFragment.getInstance().setupIdlingBuilder();
        idlingResource = mIdlingBuilder.getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }


    @Test
    public void clickOnRecyclerViewItem_opensRecipeStepDetailsActivity() {

        Intent intent = new Intent();
        intent.putExtra("RecipeId", EXPECTED_ID);
        recipesListDetailsActivityTestRule.launchActivity(intent);

        onView(ViewMatchers.withId(R.id.ingredientList)).check(matches(isDisplayed()));
        onView(withId(android.R.id.content)).perform(ViewActions.swipeUp());
        onView(ViewMatchers.withId(R.id.directionList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Assert.assertEquals(getActivityInstance().getClass(), RecipeStepDetailsActivity.class);
    }

    private Activity getActivityInstance() {
        final Activity[] currentActivity = {null};
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity[0] = (Activity) resumedActivities.iterator().next();
                }
            }
        });
        return currentActivity[0];
    }


    @After
    public void unRegisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}
