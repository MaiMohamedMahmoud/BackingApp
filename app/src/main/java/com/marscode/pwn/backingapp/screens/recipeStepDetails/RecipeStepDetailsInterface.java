package com.marscode.pwn.backingapp.screens.recipeStepDetails;

import android.net.Uri;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.marscode.pwn.backingapp.Model.Direction;

import java.util.List;

public interface RecipeStepDetailsInterface {

    interface RecipeStepDetailsModel {
        String getStepVideoUrl(Direction direction);


        Direction getStepDetailsSharedPref(int id);

        List<Direction> getStepsSharedPref();

        String getStepDescriptionText(int id);
    }

    interface RecipeStepDetailsView {
        void setUp();

        void setPlayer(SimpleExoPlayer player);

        void setSpinnerVisibility(boolean visibility);

        void setTextDescription(String description);


    }

    interface RecipeStepDetailsPresenter {
        void initializePlayer();

        void buildMediaSource(Uri mUri);

        void releasePlayer();

        void pausePlayer();

        void resumePlayer();

        Direction getStepDetailsSharedPref(int id);

        List<Direction> getStepsSharedPref();

        String getStepVideoUrl(Direction direction);

        void getStepDescriptionText(int id);
    }
}
