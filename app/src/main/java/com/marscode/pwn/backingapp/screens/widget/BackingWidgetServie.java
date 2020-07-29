package com.marscode.pwn.backingapp.screens.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marscode.pwn.backingapp.Model.Ingredient;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;

import java.lang.reflect.Type;
import java.util.List;

public class BackingWidgetServie extends RemoteViewsService {

    static int recipe_id = 0;

    public static List<Recipe> getSharedPrefRecipes(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.recipeListPref), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(context.getString(R.string.recipeListObj), "");
        Type type = new TypeToken<List<Recipe>>() {
        }.getType();
        List<Recipe> recipeObj = gson.fromJson(json, type);

        return recipeObj;
    }

    public static void updateWidget(Context context, int id) {

        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.recipeItemPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();

        List<Recipe> recipeObj = getSharedPrefRecipes(context);
        Recipe recipe = recipeObj.get(id);
        String recipe_json = gson.toJson(recipe);
        prefsEditor.putString(context.getString(R.string.recipeObjWidget), recipe_json);
        prefsEditor.commit();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BackingAppWidget.class));
        BackingAppWidget.updateAppWidgets(context, appWidgetManager, appWidgetIds);


    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        List<Ingredient> IngredientList = getSharedPrefRecipes(getApplicationContext()).get(0).getIngredients();
        return new BackingWidgetFactory(IngredientList, getApplicationContext());
    }
}
