package br.com.android.aline.recipes.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.adapter.ListIngredientsAdapter;
import br.com.android.aline.recipes.models.Ingredient;


public class IngredientFragment extends Fragment {
    private RecyclerView mRecyclerIngredient;
    private static final String LAYOUT_STATE = "LAYOUT_STATE";
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Ingredient> listIngredient;


    public void setListIngredient(List<Ingredient> listIngredient) {
        this.listIngredient = listIngredient;
    }

    public IngredientFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View rootView =  inflater.inflate(R.layout.fragment_ingredient, container, false);

         mRecyclerIngredient = rootView.findViewById(R.id.recycler_ingredient);
         setupRecycler();
         return rootView;
    }

    private void setupRecycler() {
        mRecyclerIngredient.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerIngredient.setLayoutManager(mLayoutManager);
        mRecyclerIngredient.setAdapter(new ListIngredientsAdapter(listIngredient,getContext()));

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LAYOUT_STATE, mRecyclerIngredient.getLayoutManager().onSaveInstanceState());
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) listIngredient);
        outState.putBundle("list", bundle);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            mLayoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_STATE));
            Bundle bundle = savedInstanceState.getBundle("list");
            listIngredient = (List<Ingredient>) bundle.getSerializable("list");
            mRecyclerIngredient.setAdapter(new ListIngredientsAdapter(listIngredient,getContext()));
        }

    }





}
