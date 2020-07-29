package com.marscode.pwn.backingapp.Network;


import com.marscode.pwn.backingapp.Model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipesService {
    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
