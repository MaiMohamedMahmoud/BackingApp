package com.marscode.pwn.backingapp.screens.recipelist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marscode.pwn.backingapp.Model.Recipe;
import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.IdlingBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    List<Recipe> recipeList;
    Context context;
    RecipeListInterface.RecipeListView mRecipeListView;
    boolean twoPane;
    IdlingBuilder idlingBuilder;

    RecipeListAdapter(Context context, List<Recipe> recipeList, RecipeListInterface.RecipeListView recipeListView, boolean twoPane, IdlingBuilder mIdlingBuilder) {
        this.context = context;
        this.recipeList = recipeList;
        mRecipeListView = recipeListView;
        this.twoPane = twoPane;
        idlingBuilder = mIdlingBuilder;
    }

    @NonNull
    @Override
    public RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder {

        ImageView recipeImage;
        TextView recipeServings;
        TextView recipeDescription;
        View itemView;

        public RecipeListViewHolder(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
        }

        private void setView(View itemView) {
            recipeImage = itemView.findViewById(R.id.recipe_image);
            recipeServings = itemView.findViewById(R.id.recipe_servings);
            recipeDescription = itemView.findViewById(R.id.recipe_description);
            this.itemView = itemView;

        }

        public void bind(final int position) {
//            int resource_Of_Image = context.getResources()
//                    .getIdentifier(recipeList.get(position).getName().toLowerCase(Locale.getDefault()).replace(" ", ""), "drawable", context.getPackageName());

            int resource_Of_Image = R.drawable.recipeimage;
            Picasso.with((context)).
                    load(resource_Of_Image).
                    into(recipeImage);

            idlingBuilder.setIdleState(true);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (twoPane) {
                        Log.i("yarab", twoPane + "RecipeListAdapter");
                        mRecipeListView.getRecipeDetailsTwoPan(position);
                    } else {
                        mRecipeListView.getRecipeDetailsId(position);

                    }
                }
            });
            Log.i("hello", resource_Of_Image + "");
            recipeDescription.setText(recipeList.get(position).getName());
            recipeServings.setText(context.getString(R.string.serving_txt)+" "+recipeList.get(position).getServings());
        }
    }
}
