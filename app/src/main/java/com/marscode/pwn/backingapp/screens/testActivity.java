package com.marscode.pwn.backingapp.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.recipeStepDetails.RecipeStepDetailsFragment;

public class testActivity  extends FragmentBuilder {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setFragment(R.id.fragment_container, new testFragment());

    }
}
