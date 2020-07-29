package com.marscode.pwn.backingapp.screens.recipeStepDetails;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;

import java.util.List;

public class RecipeStepDetailsinteractor implements RecipeStepDetailsInterface.RecipeStepDetailsModel {
    Context mContext;

    public RecipeStepDetailsinteractor(Context context) {
        mContext = context;
    }

    @Override
    public String getStepVideoUrl(Direction direction) {
        return direction.getVideoURL();
    }

    @Override
    public Direction getStepDetailsSharedPref(int position) {
        return getRecipeSharedPrefObject().getDirections().get(position);
    }

    @Override
    public List<Direction> getStepsSharedPref() {
        return getRecipeSharedPrefObject().getDirections();
    }

    @Override
    public String getStepDescriptionText(int id) {
        return getRecipeSharedPrefObject().getDirections().get(id).getDescription();
    }

    private Recipe getRecipeSharedPrefObject() {
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.recipeItemPref), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(mContext.getString(R.string.recipeObj), "");
        Recipe recipeObj = gson.fromJson(json, Recipe.class);

        return recipeObj;
    }

}
