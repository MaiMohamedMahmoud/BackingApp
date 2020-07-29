package com.marscode.pwn.backingapp.screens.recipeStepDetails;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.IdlingBuilder;
import com.marscode.pwn.backingapp.screens.Network;
import com.marscode.pwn.backingapp.screens.recipelist.RecipesListFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class RecipeStepDetailsFragment extends Fragment implements RecipeStepDetailsInterface.RecipeStepDetailsView {

    PlayerView videoFullScreenPlayer;
    ProgressBar spinnerVideoDetails;
    String videoUri;
    RecipeStepDetailsPresenter recipeStepDetailsPresenter;
    TextView stepDiscreption;
    int stepId;
    private Network mNetwork;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);
        videoFullScreenPlayer = view.findViewById(R.id.videoFullScreenPlayer);
        spinnerVideoDetails = view.findViewById(R.id.spinnerVideoDetails);
        stepDiscreption = view.findViewById(R.id.txt_step_details_description);
        Bundle args = getArguments();
        stepId = args.getInt("aa");

        //calling setup method
        mNetwork = new Network();
        if (mNetwork.isOnline() && mNetwork.isNetworkAvailable(getContext())) {

            recipeStepDetailsPresenter = new RecipeStepDetailsPresenter(new RecipeStepDetailsinteractor(getContext()), this, getActivity());
            videoUri = recipeStepDetailsPresenter.getStepVideoUrl(recipeStepDetailsPresenter.getStepDetailsSharedPref(stepId));
            checkUrl(videoUri);
            recipeStepDetailsPresenter.getStepDescriptionText(stepId);
        } else {
            Toast.makeText(getContext(), R.string.internet_connection, Toast.LENGTH_LONG).show();
        }
        return view;
    }


    private void checkUrl(String videoUri) {
        if (videoUri.isEmpty()) {
            videoFullScreenPlayer.setVisibility(View.GONE);
            spinnerVideoDetails.setVisibility(View.GONE);
        } else {
            videoFullScreenPlayer.setVisibility(View.VISIBLE);
            spinnerVideoDetails.setVisibility(View.VISIBLE);
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
            setUp();
        }
    }

    @Override
    public void setUp() {
        recipeStepDetailsPresenter.initializePlayer();
        if (videoUri == null) {
            return;
        }
        recipeStepDetailsPresenter.buildMediaSource(Uri.parse(videoUri));
    }

    @Override
    public void setPlayer(SimpleExoPlayer player) {
        videoFullScreenPlayer.setPlayer(player);

    }

    @Override
    public void setSpinnerVisibility(boolean visibility) {
        if (visibility) {
            spinnerVideoDetails.setVisibility(View.VISIBLE);
        } else {
            spinnerVideoDetails.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTextDescription(String description) {
        stepDiscreption.setText(description);
    }


    @Override
    public void onPause() {
        super.onPause();
        recipeStepDetailsPresenter.pausePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        recipeStepDetailsPresenter.resumePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recipeStepDetailsPresenter.releasePlayer();
    }
}