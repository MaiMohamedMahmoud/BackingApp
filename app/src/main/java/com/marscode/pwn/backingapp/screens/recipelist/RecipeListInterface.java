package com.marscode.pwn.backingapp.screens.recipelist;

import android.content.Context;

import com.marscode.pwn.backingapp.Model.Recipe;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Callback;

public interface RecipeListInterface {

    interface RecipeListModel {
        void fetchRecipes(Callback<List<Recipe>> recipes);

        void setRecipes(List<Recipe> recipes);

        List<Recipe> getRecipe();
    }

    interface RecipeListView {
        void setListAdapter(List<Recipe> recipes);

        void getRecipeDetailsId(int id);

        void getRecipeDetailsTwoPan(int id);
    }

    interface RecipeListPresenter {

        void fetchRecipes();

        void setRecipes(List<Recipe> recipes);

        List<Recipe> getRecipe();

        void setListRecipeSharedPreference(List<Recipe> list);

        void setRecipeSharedPreference(int id);


    }
}
