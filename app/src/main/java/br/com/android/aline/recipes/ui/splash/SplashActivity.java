package br.com.android.aline.recipes.ui.splash;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.test.espresso.IdlingResource;
import com.wang.avi.AVLoadingIndicatorView;
import java.io.IOException;
import java.util.List;
import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.RecipeIdlingResource;
import br.com.android.aline.recipes.cache.InternalStorage;
import br.com.android.aline.recipes.models.Result;
import br.com.android.aline.recipes.repository.RecipesViewModel;
import br.com.android.aline.recipes.ui.home.HomeActivity;
import br.com.android.aline.recipes.utils.Utils;

public class SplashActivity extends AppCompatActivity implements ISplashView {
    private static final String KEY = "LIST_RECIPES";
    private static final String LOG_TAG = SplashActivity.class.getName() ;
    private AVLoadingIndicatorView progress;

    @Nullable private RecipeIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progress = findViewById(R.id.avi_progress);
        getIdlingResource();

        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        startProgress();
        isAvailableConnection(this);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new RecipeIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    public void isAvailableConnection(Context context) {
        if(Utils.statusNetworkInfo(context)){
            initViewModelRecipes();
        }else{
            hideProgress();
            showMessage(getString(R.string.message_failed_internet));
        }
    }

    private void initViewModelRecipes() {
        RecipesViewModel recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);
        recipesViewModel.getNewRecipe().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {

              if (mIdlingResource != null) {
                    mIdlingResource.setIdleState(true);
                }
                hideProgress();
                saveInternalStorage(results);
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.layout_splash), message, Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isAvailableConnection(SplashActivity.this);
                    }
                }).show();
    }

    @Override
    public void startProgress() {
        progress.show();
    }

    @Override
    public void hideProgress() {
        progress.hide();
    }

    @Override
    public void saveInternalStorage(List<Result> mListRecipe) {
        try{
            InternalStorage.writeObject(this, KEY, mListRecipe);
        }catch (IOException e){
            Log.e(LOG_TAG, "Error-- " + e.getMessage());
        }
    }

}
