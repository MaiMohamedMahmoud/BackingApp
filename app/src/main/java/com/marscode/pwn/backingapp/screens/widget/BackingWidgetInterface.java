package com.marscode.pwn.backingapp.screens.widget;

import com.marscode.pwn.backingapp.Model.Recipe;

import java.util.List;

public interface BackingWidgetInterface {

    interface BackingWidgetModel {
        List<Recipe> getSharedPrefRecipes();
    }

    interface BackingWidgetView {

    }

    interface BackingWidgetPresenter {
        List<Recipe> getSharedPrefRecipes();
    }

}
