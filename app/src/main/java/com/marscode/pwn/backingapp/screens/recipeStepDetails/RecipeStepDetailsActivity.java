package com.marscode.pwn.backingapp.screens.recipeStepDetails;

import android.os.Bundle;

import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.FragmentBuilder;
import com.marscode.pwn.backingapp.screens.recipeDetails.RecipeDetailsFragment;

import androidx.annotation.Nullable;

public class RecipeStepDetailsActivity extends FragmentBuilder {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);
        setFragment(R.id.fragment_step_details_container, new RecipeStepDetailsViewPagerFragment());
    }
}
