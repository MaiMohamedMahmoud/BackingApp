package com.marscode.pwn.backingapp.screens;

import android.content.Context;

import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.recipelist.RecipesListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentBuilder extends AppCompatActivity {


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
}
