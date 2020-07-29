package com.marscode.pwn.backingapp.Network;

import com.marscode.pwn.backingapp.Model.Recipe;

public class ApiUtils {

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public static RecipesService getRecipesService() {
        return RetrofitClient.getClient(BASE_URL).create(RecipesService.class);
    }


}
