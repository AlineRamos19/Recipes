package br.com.android.aline.recipes.ui.splash;


import android.content.Context;

import java.util.List;

import br.com.android.aline.recipes.models.Result;

public interface ISplashView {

    void isAvailableConnection(Context context);
    void showMessage(String message);
    void startProgress();
    void hideProgress();
    void saveInternalStorage(List<Result> mListRecipe);
}
