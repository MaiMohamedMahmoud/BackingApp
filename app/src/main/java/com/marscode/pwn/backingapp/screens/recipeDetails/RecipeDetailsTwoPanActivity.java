package com.marscode.pwn.backingapp.screens.recipeDetails;

import android.os.Bundle;

import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.FragmentBuilder;
import com.marscode.pwn.backingapp.screens.recipeStepDetails.RecipeStepDetailsFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class RecipeDetailsTwoPanActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recipe_details);
        super.onCreate(savedInstanceState);

        setFragment(R.id.fragment_container_activity_details, new RecipeDetailsFragment());
        setSecFragment(R.id.fragment_step_details_container, new RecipeStepDetailsFragment());
    }

    public void setFragment(int resId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentById = fragmentManager.findFragmentById(resId);//R.id.fragment_container);
        if (fragmentById == null) {
            fragmentById = fragment;//new RecipesListFragment();
            fragmentManager.beginTransaction()
                    .add(resId, fragmentById).
                    commit();
        }
    }

    public void setSecFragment(int resId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentById = fragmentManager.findFragmentById(resId);//R.id.fragment_container);
        Bundle args = new Bundle();
        args.putInt("aa", 0);
        fragment.setArguments(args);
        if (fragmentById == null) {
            fragmentById = fragment;//new RecipesListFragment();
            fragmentManager.beginTransaction()
                    .add(resId, fragmentById).
                    commit();
        }
    }



}
