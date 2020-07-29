package com.marscode.pwn.backingapp.screens.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;

import java.lang.reflect.Type;
import java.util.List;

public class BackingWidgetInteractor implements BackingWidgetInterface.BackingWidgetModel {
    Context context;

    BackingWidgetInteractor(Context context) {
        this.context = context;
    }

    @Override
    public List<Recipe> getSharedPrefRecipes() {

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.recipeListPref), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(context.getString(R.string.recipeListObj), "");
        Type type = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipeObj = gson.fromJson(json, type);
        Log.i("yarab", recipeObj.get(0).getName());
        return recipeObj;
    }
}
