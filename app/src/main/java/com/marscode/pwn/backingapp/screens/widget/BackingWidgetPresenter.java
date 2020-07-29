package com.marscode.pwn.backingapp.screens.widget;

import com.marscode.pwn.backingapp.Model.Recipe;

import java.util.List;

public class BackingWidgetPresenter implements BackingWidgetInterface.BackingWidgetPresenter {
    BackingWidgetInteractor backingWidgetInteractor;

    BackingWidgetPresenter(BackingWidgetInteractor backingWidgetInteractor) {
        this.backingWidgetInteractor = backingWidgetInteractor;
    }

    @Override
    public List<Recipe> getSharedPrefRecipes() {
        return backingWidgetInteractor.getSharedPrefRecipes();
    }
}
