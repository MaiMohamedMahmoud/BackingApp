package com.marscode.pwn.backingapp.screens.recipeDetails;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.IdlingBuilder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DirectionListAdapter extends RecyclerView.Adapter<DirectionListAdapter.DirectionViewHolder> {

    RecipeDetailsInterface.RecipeDetailsView recipeDetailsView;
    List<Direction> directionList;
    boolean mTwoPane;
    IdlingBuilder idlingBuilder;

    DirectionListAdapter(List<Direction> directionList, RecipeDetailsInterface.RecipeDetailsView recipeDetailsView, boolean twoPane, IdlingBuilder idlingBuilder) {
        this.directionList = directionList;
        this.recipeDetailsView = recipeDetailsView;
        mTwoPane = twoPane;
        this.idlingBuilder = idlingBuilder;
    }

    @NonNull
    @Override
    public DirectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.description_item, parent, false);
        return new DirectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectionViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return directionList.size();
    }

    public class DirectionViewHolder extends RecyclerView.ViewHolder {

        TextView directionTxt;
        TextView directionNumberTxt;
        ImageView directionNumberImg;

        public DirectionViewHolder(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
        }

        public void setView(View itemView) {

            directionTxt = itemView.findViewById(R.id.direction_step_txt);
            directionNumberTxt = itemView.findViewById(R.id.direction_step_number_txt);
            directionNumberImg = itemView.findViewById(R.id.play_arrow_id);
            //directionNumberImg = itemView.findViewById(R.id.direction_step_number_img);

        }

        public void bind(final int position) {
//            String step = String.valueOf(directionList.get(position).getId());
//            ColorGenerator generator = ColorGenerator.MATERIAL;
//            //Create a new TextDrawable for our image's background
//            TextDrawable drawable = TextDrawable.builder()
//                    .buildRound(step, R.color.colorPrimary);
//            directionNumberImg.setImageDrawable(drawable);

            directionNumberTxt.setText(directionList.get(position).getId() + "");
            directionTxt.setText(directionList.get(position).getShortDescription());

            if (!directionList.get(position).getVideoURL().isEmpty()) {
                Log.i("yaraab", directionList.get(position).getVideoURL() + " " + directionList.get(position).getId() + " " + position);
                directionNumberImg.setVisibility(View.VISIBLE);

              //  idlingBuilder.setIdleState(true);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        recipeDetailsView.getRecipeStepDetails(position);


                    }
                });
            } else {
                directionNumberImg.setVisibility(View.GONE);
            }
        }
    }
}
