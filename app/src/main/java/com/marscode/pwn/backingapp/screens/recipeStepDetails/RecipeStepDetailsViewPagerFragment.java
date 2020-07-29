package com.marscode.pwn.backingapp.screens.recipeStepDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.marscode.pwn.backingapp.Model.Direction;
import com.marscode.pwn.backingapp.R;
import com.marscode.pwn.backingapp.screens.IdlingBuilder;
import com.marscode.pwn.backingapp.screens.Network;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class RecipeStepDetailsViewPagerFragment extends Fragment {
    RecipeStepDetailsPagerAdapter mRecipeStepDetailsPagerAdapter;
    ViewPager viewPager;
    RecipeStepDetailsPresenter recipeStepDetailsPresenter;
    List<Direction> mDirections;
    int stepId;
    IdlingBuilder mIdlingBuilder;
    private Network mNetwork;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step_details_viewpager, container, false);
        //get stepId then use it to get the stepdetails object
        stepId = getActivity().getIntent().getExtras().getInt(String.valueOf(R.string.step_detail_id));
        mNetwork = new Network();
        if (mNetwork.isOnline() && mNetwork.isNetworkAvailable(getContext())) {

            setupIdlingBuilder();
            mIdlingBuilder.setIdleState(false);

            recipeStepDetailsPresenter = new RecipeStepDetailsPresenter(new RecipeStepDetailsinteractor(getContext()), getActivity(), mIdlingBuilder);
            mDirections = recipeStepDetailsPresenter.getStepsSharedPref();
        } else {
            Toast.makeText(getContext(), R.string.internet_connection, Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @VisibleForTesting
    @NonNull
    public static RecipeStepDetailsViewPagerFragment getInstance() {
        return new RecipeStepDetailsViewPagerFragment();
    }

    public IdlingBuilder setupIdlingBuilder() {
        mIdlingBuilder = new IdlingBuilder();
        return mIdlingBuilder;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecipeStepDetailsPagerAdapter = new RecipeStepDetailsPagerAdapter(getChildFragmentManager(), stepId, mDirections, 0);
        viewPager = view.findViewById(R.id.pager_recipe_step_details);
        viewPager.setAdapter(mRecipeStepDetailsPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(stepId);
    }
}
