package com.marscode.pwn.backingapp.screens;

import android.app.Application;

import com.marscode.pwn.backingapp.BuildConfig;
import com.marscode.pwn.backingapp.Model.RecipeIdlingResource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.test.espresso.IdlingResource;

public class IdlingBuilder extends Application {
    // The Idling Resource which will be null in production.
    @Nullable
    private RecipeIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link RecipeIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    private IdlingResource initializeIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new RecipeIdlingResource();
        }
        return mIdlingResource;
    }

    public IdlingBuilder() {

        // The IdlingResource will be null in production.
        if (BuildConfig.DEBUG) {
            initializeIdlingResource();
        }

    }

    public void setIdleState(boolean state) {
        if (mIdlingResource != null)
            mIdlingResource.setIdleState(state);
    }

    @Nullable
    public IdlingResource getIdlingResource() {
        return mIdlingResource;
    }
}
