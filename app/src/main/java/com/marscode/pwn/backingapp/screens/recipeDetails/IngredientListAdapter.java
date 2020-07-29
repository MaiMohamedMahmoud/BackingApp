package com.marscode.pwn.backingapp.screens.recipeDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.marscode.pwn.backingapp.Model.Ingredient;
import com.marscode.pwn.backingapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {

    List<Ingredient> ingredientList;

    IngredientListAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return this.ingredientList.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        CheckBox ingredientCheckBox;
        TextView quantity;
        TextView measure;
        TextView ingredient;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
        }

        private void setView(View itemView) {
          //  ingredientCheckBox = itemView.findViewById(R.id.checkBox_ingredient);
            quantity = itemView.findViewById(R.id.txt_quantity);
            measure = itemView.findViewById(R.id.txt_measure);
            ingredient = itemView.findViewById(R.id.txt_ingredient);

        }

        private void bind(int position) {
            measure.setText(ingredientList.get(position).getMeasure());
            quantity.setText(ingredientList.get(position).getQuantity() + "");
            ingredient.setText(ingredientList.get(position).getIngredient());

        }
    }

}
