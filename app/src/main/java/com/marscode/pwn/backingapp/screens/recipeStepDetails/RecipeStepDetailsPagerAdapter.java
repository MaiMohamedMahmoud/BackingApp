package com.marscode.pwn.backingapp.screens.recipeStepDetails;

import android.os.Bundle;

import com.marscode.pwn.backingapp.Model.Direction;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class RecipeStepDetailsPagerAdapter extends FragmentStatePagerAdapter {
    List<Direction> directionList;
    int stepId;

    public RecipeStepDetailsPagerAdapter(@NonNull FragmentManager fm, int stepId, List<Direction> directionList, int behavior) {
        super(fm, behavior);
        this.directionList = directionList;
        this.stepId = stepId;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new RecipeStepDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("aa", directionList.get(position).getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return directionList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "STEP " + (position);
    }
}
