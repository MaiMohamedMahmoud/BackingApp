package com.marscode.pwn.backingapp.screens.recipeStepDetails;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.IdlingBuilder;

import java.util.List;

public class RecipeStepDetailsPresenter implements RecipeStepDetailsInterface.RecipeStepDetailsPresenter, Player.EventListener {

    RecipeStepDetailsInterface.RecipeStepDetailsView mRecipeStepDetailsView;
    RecipeStepDetailsInterface.RecipeStepDetailsModel mRecipeStepDetailsModel;
    SimpleExoPlayer player;
    Context mContext;
    IdlingBuilder mIdlingBuilder;

    public RecipeStepDetailsPresenter(RecipeStepDetailsInterface.RecipeStepDetailsModel recipeStepDetailsModel, RecipeStepDetailsInterface.RecipeStepDetailsView recipeStepDetailsView, Context context) {
        mRecipeStepDetailsModel = recipeStepDetailsModel;
        mRecipeStepDetailsView = recipeStepDetailsView;
        mContext = context;
    }

    public RecipeStepDetailsPresenter(RecipeStepDetailsInterface.RecipeStepDetailsModel recipeStepDetailsModel, Context context, IdlingBuilder idlingBuilder) {
        mRecipeStepDetailsModel = recipeStepDetailsModel;
        mContext = context;
        mIdlingBuilder = idlingBuilder;
    }

    @Override
    public void initializePlayer() {
        if (player == null) {
            // 1. Create a default TrackSelector
            LoadControl loadControl = new DefaultLoadControl(
                    new DefaultAllocator(true, 16),
                    VideoPlayerConfig.MIN_BUFFER_DURATION,
                    VideoPlayerConfig.MAX_BUFFER_DURATION,
                    VideoPlayerConfig.MIN_PLAYBACK_START_BUFFER,
                    VideoPlayerConfig.MIN_PLAYBACK_RESUME_BUFFER, -1, true);
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector =
                    new DefaultTrackSelector(videoTrackSelectionFactory);
            // 2. Create the player
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(mContext.getApplicationContext()), trackSelector,
                    loadControl);
            //mIdlingBuilder.setIdleState(true);
            mRecipeStepDetailsView.setPlayer(player);


        }
    }

    @Override
    public void buildMediaSource(Uri mUri) {
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext.getApplicationContext(),
                Util.getUserAgent(mContext.getApplicationContext(), mContext.getString(R.string.app_name)), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mUri);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
        player.addListener(this);
    }

    @Override
    public void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }


    @Override
    public void pausePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        }
    }

    @Override
    public void resumePlayer() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
        }
    }

    @Override
    public Direction getStepDetailsSharedPref(int id) {
        return mRecipeStepDetailsModel.getStepDetailsSharedPref(id);
    }

    @Override
    public List<Direction> getStepsSharedPref() {
        return mRecipeStepDetailsModel.getStepsSharedPref();
    }

    @Override
    public String getStepVideoUrl(Direction direction) {
        return mRecipeStepDetailsModel.getStepVideoUrl(direction);
    }

    @Override
    public void getStepDescriptionText(int id) {
        String description = mRecipeStepDetailsModel.getStepDescriptionText(id);
        mRecipeStepDetailsView.setTextDescription(description);
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {
            case Player.STATE_BUFFERING:
                mRecipeStepDetailsView.setSpinnerVisibility(true);
                break;
            case Player.STATE_ENDED:
                // Activate the force enable
                break;
            case Player.STATE_IDLE:
                break;
            case Player.STATE_READY:
                mRecipeStepDetailsView.setSpinnerVisibility(false);
                break;
            default:
                // status = PlaybackStatus.IDLE;
                break;
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
}
