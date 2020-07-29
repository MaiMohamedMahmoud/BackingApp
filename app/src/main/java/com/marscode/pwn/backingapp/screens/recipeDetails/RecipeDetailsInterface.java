package com.marscode.pwn.backingapp.screens.recipeDetails;

import com.marscode.pwn.backingapp.Model.Ingredient;
import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.Model.Recipe;

import java.util.List;

public interface RecipeDetailsInterface {

    interface RecipeDetailsInteractor {

        List<Ingredient> getRecipeIngredients(Recipe recipe);

        List<Direction> getRecipeDirections(Recipe recipe);

        String getRecipeDetailsImage(Recipe recipe);


    }

    interface RecipeDetailsView {
        void setIngredientListAdapter(List<Ingredient> ingredientList);

        void setDirectionListAdapter(List<Direction> directionList);

        void setRecipeDetailsImage(String recipeName);

        Recipe getRecipeSharedPrefrence();

        void getRecipeStepDetails(int id);

        void setToast();
    }


    interface RecipeDetailsPresenter {
        List<Ingredient> getRecipeIngredients(Recipe recipe);

        List<Direction> getRecipeDirections(Recipe recipe);

        String getRecipeDetailsImage(Recipe recipe);

        void setIngredient();

        void setDirection();

        void setDetailsImage();

        Recipe getRecipeObject();
    }
}
