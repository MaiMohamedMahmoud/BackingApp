package com.marscode.pwn.backingapp.screens.recipelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.FragmentBuilder;

public class RecipesListActivity extends  FragmentBuilder {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeslist);

        setFragment(R.id.fragment_container, new RecipesListFragment());


    }
}
