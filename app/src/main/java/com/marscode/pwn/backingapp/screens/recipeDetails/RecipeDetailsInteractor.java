package com.marscode.pwn.backingapp.screens.recipeDetails;

import com.marscode.pwn.backingapp.Model.Ingredient;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.screens.recipelist.RecipeListInterface;
import com.marscode.pwn.backingapp.screens.recipelist.RecipeListPresenter;

import java.util.List;

public class RecipeDetailsInteractor implements RecipeDetailsInterface.RecipeDetailsInteractor {

    @Override
    public List<Ingredient> getRecipeIngredients(Recipe recipe) {

        List<Ingredient> ingredients = recipe.getIngredients();
        return ingredients;
    }

    @Override
    public List<Direction> getRecipeDirections(Recipe recipe) {
        List<Direction> directionSteps = recipe.getDirections();
        return directionSteps;
    }

    @Override
    public String getRecipeDetailsImage(Recipe recipe) {
        return recipe.getName();
    }


}
