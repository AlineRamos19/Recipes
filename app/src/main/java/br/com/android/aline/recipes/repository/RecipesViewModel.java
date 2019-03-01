package br.com.android.aline.recipes.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.android.aline.recipes.models.Result;

public class RecipesViewModel extends AndroidViewModel {

    private LiveData<List<Result>> mListRecipe;
    private RecipesRepository mRecipesRepository;

    public RecipesViewModel(@NonNull Application application) {
        super(application);
        mRecipesRepository = RecipesRepository.getInstance();
    }

    public LiveData<List<Result>> getNewRecipe() {

        if (mListRecipe == null) {
            mListRecipe = new MutableLiveData<>();
            mListRecipe = mRecipesRepository.getRecipes();
        }
        return mListRecipe;
    }
}
