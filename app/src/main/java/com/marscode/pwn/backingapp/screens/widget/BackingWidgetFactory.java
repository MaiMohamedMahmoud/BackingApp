package com.marscode.pwn.backingapp.screens.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.marscode.pwn.backingapp.Model.Ingredient;
import com.marscode.pwn.backingapp.Model.PicassoCircleTransformation;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BackingWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    List<Ingredient> ingredientList;

    BackingWidgetFactory(List<Ingredient> ingredientList, Context context) {
        mContext = context;
        this.ingredientList = ingredientList;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.recipeItemPref), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(mContext.getString(R.string.recipeObjWidget), "");
        Recipe recipeObj = gson.fromJson(json, Recipe.class);

        ingredientList = recipeObj.getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        //here return the Recipes Count
        return ingredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        //here build your item
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.backing_app_widget_item_list);
        remoteViews.setImageViewResource(R.id.image_recipe_widget, R.drawable.widgetrecipes);
        remoteViews.setTextViewText(R.id.recipe_txt_widget, ingredientList.get(i).getIngredient());
        remoteViews.setTextViewText(R.id.serving_number_txt_widget, " " + ingredientList.get(i).getQuantity() + ingredientList.get(i).getQuantity());

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
