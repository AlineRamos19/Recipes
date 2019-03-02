package br.com.android.aline.recipes;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.android.aline.recipes.ui.menurecipes.MenuRecipeActivity;

@RunWith(AndroidJUnit4.class)
public class MenuRecipeTest {

    @Rule
    public IntentsTestRule<MenuRecipeActivity> mIntentTest =
            new IntentsTestRule<>(MenuRecipeActivity.class);

    @Test
    public void clickBtnSteps_goDetailsStep(){

    }
}
