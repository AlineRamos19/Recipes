package br.com.android.aline.recipes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.adapter.ListRecipesAdapter;
import br.com.android.aline.recipes.cache.InternalStorage;
import br.com.android.aline.recipes.models.Result;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterListFragment extends Fragment {

    private List<Result> mListAllRecipes;
    private static final String KEY = "LIST_RECIPES";
    private RecyclerView mRecipesRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String LAYOUT_STATE = "LAYOUT_STATE";
    OnRecipeClickListener mCallback;

    public MasterListFragment() {

    }

    public interface OnRecipeClickListener{
        void onRecipeSelected(Result result);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnRecipeClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
            + "must implement OnRecipeClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        mRecipesRecycler = rootView.findViewById(R.id.list_recipes);

        setupAdapter();

        return rootView;

    }

    private void setupAdapter() {
        mRecipesRecycler.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecipesRecycler.setLayoutManager(mLayoutManager);
        ListRecipesAdapter adapter = new ListRecipesAdapter(getContext(), getListCache(),mCallback);
        mRecipesRecycler.setAdapter(adapter);

    }


    public List<Result> getListCache() {
        try {
            mListAllRecipes = (List<Result>) InternalStorage.readObject(getContext(), KEY);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mListAllRecipes;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LAYOUT_STATE, mRecipesRecycler.getLayoutManager().onSaveInstanceState());

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            mLayoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_STATE));
        }

    }
}
