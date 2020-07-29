package com.marscode.pwn.backingapp.screens.recipeDetails;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.Model.Ingredient;
import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.FragmentBuilder;
import com.marscode.pwn.backingapp.screens.IdlingBuilder;
import com.marscode.pwn.backingapp.screens.Network;
import com.marscode.pwn.backingapp.screens.recipeStepDetails.RecipeStepDetailsActivity;
import com.marscode.pwn.backingapp.screens.recipeStepDetails.RecipeStepDetailsFragment;
import com.marscode.pwn.backingapp.screens.recipelist.RecipeListInteractor;
import com.marscode.pwn.backingapp.screens.recipelist.RecipeListInterface;
import com.marscode.pwn.backingapp.screens.recipelist.RecipeListPresenter;
import com.marscode.pwn.backingapp.screens.recipelist.RecipesListActivity;
import com.marscode.pwn.backingapp.screens.recipelist.RecipesListFragment;
import com.marscode.pwn.backingapp.screens.widget.BackingWidgetServie;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailsFragment extends Fragment implements RecipeDetailsInterface.RecipeDetailsView {
    RecyclerView ingredientList;
    RecyclerView directionList;
    ImageView recipeDetailsImage;
    IngredientListAdapter mIngredientListAdapter;
    DirectionListAdapter mDirectionListAdapter;
    RecipeDetailsPresenter mRecipeDetailsPresenter;
    RecipeDetailsInteractor mRecipeDetailsInteractor;
    Intent mIntent;
    private boolean mTwoPane;
    IdlingBuilder mIdlingBuilder;
    int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ingredientList = view.findViewById(R.id.ingredientList);
        directionList = view.findViewById(R.id.directionList);
        // recipeDetailsImage = view.findViewById(R.id.recipe_details_image);
        mRecipeDetailsInteractor = new RecipeDetailsInteractor();
        mRecipeDetailsPresenter = new RecipeDetailsPresenter(mRecipeDetailsInteractor, this);
        /**
         *  If this view is present, then the
         *  activity should be in two-pane mode.
         *  mTwoPane is true
         * */
        mTwoPane = getResources().getBoolean(R.bool.twoPaneMode);
        id = getActivity().getIntent().getExtras().getInt(String.valueOf(R.string.recipe_id));
        /**
         * get the recipe object using shared prefrence
         */
        // mRecipeDetailsPresenter.setDetailsImage();

        mRecipeDetailsPresenter.setIngredient();
        mRecipeDetailsPresenter.setDirection();


        setupIdlingBuilder();
        mIdlingBuilder.setIdleState(false);

        setHasOptionsMenu(true);
        return view;
    }

    @VisibleForTesting
    @NonNull
    public static RecipeDetailsFragment getInstance() {
        return new RecipeDetailsFragment();
    }

    public IdlingBuilder setupIdlingBuilder() {
        mIdlingBuilder = new IdlingBuilder();
        return mIdlingBuilder;
    }


    @Override
    public void setIngredientListAdapter(List<Ingredient> ingredients) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext(), RecyclerView.VERTICAL, false);
        ingredientList.setLayoutManager(linearLayoutManager);
        mIngredientListAdapter = new IngredientListAdapter(ingredients);
        ingredientList.setAdapter(mIngredientListAdapter);
    }

    @Override
    public void setDirectionListAdapter(List<Direction> directions) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext(), RecyclerView.VERTICAL, false);
        directionList.setLayoutManager(linearLayoutManager);
        mDirectionListAdapter = new DirectionListAdapter(directions, this, mTwoPane, mIdlingBuilder);
        directionList.setAdapter(mDirectionListAdapter);
    }

    @Override
    public void setRecipeDetailsImage(String recipeName) {
        int resource_Of_Image = getContext().getResources()
                .getIdentifier(recipeName.toLowerCase(Locale.getDefault()).replace(" ", ""), "drawable", getContext().getPackageName());

        Picasso.with((getContext())).
                load(resource_Of_Image).
                into(recipeDetailsImage);
    }

    @Override
    public Recipe getRecipeSharedPrefrence() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.recipeItemPref), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(getString(R.string.recipeObj), "");
        Recipe recipeObj = gson.fromJson(json, Recipe.class);

        return recipeObj;
    }

    public void getRecipeStepDetails(int id) {

        if (mTwoPane) {
            //here open the StepDetails intent
            Bundle args = new Bundle();
            args.putInt("aa", id);

            RecipeStepDetailsFragment fragment = new RecipeStepDetailsFragment();
            fragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_step_details_container, fragment)
                    .commit();

        } else {
            //here open the StepDetails intent
            Intent intent = new Intent(getContext(), RecipeStepDetailsActivity.class);
            //Toast.makeText(getActivity().getApplicationContext(), "" + id, Toast.LENGTH_LONG).show();
            intent.putExtra(String.valueOf(R.string.step_detail_id), id);
            getActivity().startActivity(intent);

        }


    }


    @Override
    public void setToast() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.noVideo), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.add_widget_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_widget) {
            BackingWidgetServie.updateWidget(getActivity().getApplicationContext(), id);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
