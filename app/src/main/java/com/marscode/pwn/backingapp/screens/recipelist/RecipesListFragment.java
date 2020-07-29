package com.marscode.pwn.backingapp.screens.recipelist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.IdlingBuilder;
import com.marscode.pwn.backingapp.screens.Network;
import com.marscode.pwn.backingapp.screens.recipeDetails.RecipeDetailsActivity;
import com.marscode.pwn.backingapp.screens.recipeDetails.RecipeDetailsTwoPanActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

public class RecipesListFragment extends Fragment implements RecipeListInterface.RecipeListView {
    RecipeListPresenter mRecipeListPresenter;
    RecyclerView listRecipes;
    RecipeListAdapter mAdapter;
    private boolean mTwoPane;
    IdlingBuilder mIdlingBuilder;
    Network mNetwork;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipeslist, container, false);
        listRecipes = view.findViewById(R.id.recipes_list);
        mNetwork = new Network();
        RecipeListInteractor recipeListInteractor = new RecipeListInteractor();
        mRecipeListPresenter = new RecipeListPresenter(recipeListInteractor, this, getContext());
        if (mNetwork.isOnline() && mNetwork.isNetworkAvailable(getContext())) {
            mRecipeListPresenter.fetchRecipes();


            mTwoPane = getResources().getBoolean(R.bool.twoPaneMode);
            setupIdlingBuilder();
            mIdlingBuilder.setIdleState(false);
        } else {
            Toast.makeText(getContext(), R.string.internet_connection, Toast.LENGTH_LONG).show();

        }
        return view;
    }

    @VisibleForTesting
    @NonNull
    public static RecipesListFragment getInstance() {
        return new RecipesListFragment();
    }

    public IdlingBuilder setupIdlingBuilder() {
        mIdlingBuilder = new IdlingBuilder();
        return mIdlingBuilder;
    }

    @Override
    public void setListAdapter(List<Recipe> recipes) {
        mAdapter = new RecipeListAdapter(getContext(), recipes, this, mTwoPane, mIdlingBuilder);
        listRecipes.setAdapter(mAdapter);
        if (mTwoPane) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
            listRecipes.setLayoutManager(gridLayoutManager);

        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
            listRecipes.setLayoutManager(linearLayoutManager);
        }

    }

    @Override
    public void getRecipeDetailsId(int id) {
        /**
         * use sharedprefrence to pass the recipe object details to the details page ..
         */
        mRecipeListPresenter.setRecipeSharedPreference(id);

        /**
         * here open the new activity using intents
         */
        Intent intent = new Intent(getContext(), RecipeDetailsActivity.class);
        intent.putExtra(String.valueOf(R.string.recipe_id), id);
        getActivity().startActivity(intent);
    }

    @Override
    public void getRecipeDetailsTwoPan(int id) {
        /**
         * use sharedprefrence to pass the recipe object details to the details page ..
         */
        mRecipeListPresenter.setRecipeSharedPreference(id);

        /**
         * here open the new activity using intents
         */
        Intent intent = new Intent(getContext(), RecipeDetailsTwoPanActivity.class);
        intent.putExtra(String.valueOf(R.string.recipe_id), id);
        getActivity().startActivity(intent);


    }
}
