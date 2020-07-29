package com.marscode.pwn.backingapp.screens.recipelist;

import android.util.Log;

import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.Network.ApiUtils;
import com.marscode.pwn.backingapp.Network.RecipesService;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListInteractor implements RecipeListInterface.RecipeListModel {

    RecipesService mRecipesService;
    List<Recipe> listRecipe;

    public RecipeListInteractor() {
        mRecipesService = ApiUtils.getRecipesService();

    }

    @Override
    public void fetchRecipes(Callback<List<Recipe>> recipesCallback) {
        Log.i("here", "fetchRecipes");
        Call<List<Recipe>> recipesCall = mRecipesService.getRecipes();
        recipesCall.enqueue(recipesCallback);
    }

    @Override
    public void setRecipes(List<Recipe> recipes) {
        listRecipe = recipes;
    }

    @Override
    public List<Recipe> getRecipe() {
        return listRecipe;
    }


}
