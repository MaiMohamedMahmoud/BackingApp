package com.marscode.pwn.backingapp.screens.recipelist;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;

import java.util.List;
import java.util.concurrent.Callable;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListPresenter implements RecipeListInterface.RecipeListPresenter {

    RecipeListInterface.RecipeListModel recipeListInteractor;
    RecipeListInterface.RecipeListView mRecipeListView;
    Context context;


    public RecipeListPresenter(RecipeListInterface.RecipeListModel recipeListModel, RecipeListInterface.RecipeListView mRecipeListView, Context context) {
        recipeListInteractor = recipeListModel;
        this.mRecipeListView = mRecipeListView;
        this.context = context;
    }

    Callback<List<Recipe>> recipeList = new Callback<List<Recipe>>() {

        @Override
        public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
            List<Recipe> recipes = response.body();
            setRecipes(recipes);
        }

        @Override
        public void onFailure(Call<List<Recipe>> call, Throwable t) {
            Log.i("on fail", call.request() + "");
        }
    };

    @Override
    public void fetchRecipes() {
        recipeListInteractor.fetchRecipes(recipeList);
    }

    @Override
    public void setRecipes(List<Recipe> recipes) {
        recipeListInteractor.setRecipes(recipes);
        mRecipeListView.setListAdapter(recipes);
        setListRecipeSharedPreference(recipes);
    }

    @Override
    public List<Recipe> getRecipe() {
        return recipeListInteractor.getRecipe();

    }

    @Override
    public void setListRecipeSharedPreference(List<Recipe> recipes) {
        List<Recipe> recipeList = recipes;
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.recipeListPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipeList);
        prefsEditor.putString(context.getString(R.string.recipeListObj), json);
        prefsEditor.commit();
    }

    @Override
    public void setRecipeSharedPreference(int id) {
        Recipe recipe = getRecipe().get(id);
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.recipeItemPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        prefsEditor.putString(context.getString(R.string.recipeObj), json);
        prefsEditor.commit();
    }

}
