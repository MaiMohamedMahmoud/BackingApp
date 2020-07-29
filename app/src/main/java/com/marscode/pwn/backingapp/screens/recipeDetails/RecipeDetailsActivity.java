package com.marscode.pwn.backingapp.screens.recipeDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.FragmentBuilder;

public class RecipeDetailsActivity extends FragmentBuilder {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recipe_details);
        super.onCreate(savedInstanceState);
        setFragment(R.id.fragment_container_activity_details, new RecipeDetailsFragment());
    }


}
