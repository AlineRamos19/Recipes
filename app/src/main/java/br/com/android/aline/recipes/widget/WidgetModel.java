package br.com.android.aline.recipes.widget;

public class WidgetModel {

    private String mRecipeTitle;
    private int mImageRecipe;

    public WidgetModel(String mRecipeTitle, int mImageRecipe) {
        this.mRecipeTitle = mRecipeTitle;
        this.mImageRecipe = mImageRecipe;
    }

    public String getmRecipeTitle() {
        return mRecipeTitle;
    }

    public void setmRecipeTitle(String mRecipeTitle) {
        this.mRecipeTitle = mRecipeTitle;
    }

    public int getmImageRecipe() {
        return mImageRecipe;
    }

    public void setmImageRecipe(int mImageRecipe) {
        this.mImageRecipe = mImageRecipe;
    }
}
