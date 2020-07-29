package com.marscode.pwn.backingapp.screens.recipeDetails;

import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.Model.Ingredient;
import com.marscode.pwn.backingapp.Model.Recipe;

import java.util.List;

public class RecipeDetailsPresenter implements RecipeDetailsInterface.RecipeDetailsPresenter {

    RecipeDetailsInterface.RecipeDetailsInteractor mRecipeDetailsInteractor;
    RecipeDetailsInterface.RecipeDetailsView mRecipeDetailsView;

    public RecipeDetailsPresenter(RecipeDetailsInteractor recipeDetailsInteractor, RecipeDetailsInterface.RecipeDetailsView recipeDetailsView) {
        mRecipeDetailsInteractor = recipeDetailsInteractor;
        mRecipeDetailsView = recipeDetailsView;
    }

    @Override
    public List<Ingredient> getRecipeIngredients(Recipe recipe) {
        return mRecipeDetailsInteractor.getRecipeIngredients(recipe);
    }

    @Override
    public List<Direction> getRecipeDirections(Recipe recipe) {
        return mRecipeDetailsInteractor.getRecipeDirections(recipe);
    }

    @Override
    public String getRecipeDetailsImage(Recipe recipe) {
        return mRecipeDetailsInteractor.getRecipeDetailsImage(recipe);
    }

    @Override
    public void setIngredient() {
        mRecipeDetailsView.setIngredientListAdapter(getRecipeIngredients(getRecipeObject()));
    }

    @Override
    public void setDirection() {
        mRecipeDetailsView.setDirectionListAdapter(getRecipeDirections(getRecipeObject()));

    }

    @Override
    public void setDetailsImage() {
        mRecipeDetailsView.setRecipeDetailsImage(getRecipeDetailsImage(getRecipeObject()));
    }

    @Override
    public Recipe getRecipeObject() {
        return mRecipeDetailsView.getRecipeSharedPrefrence();
    }
}
