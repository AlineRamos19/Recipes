package br.com.android.aline.recipes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import br.com.android.aline.recipes.models.Ingredient;
import br.com.android.aline.recipes.models.Step;
import br.com.android.aline.recipes.ui.menurecipes.MenuRecipeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MenuRecipeTest {

    private List<Step> mListStep = new ArrayList<>();
    private List<Ingredient> mListIngredient = new ArrayList<>();

    @Rule
    public ActivityTestRule<MenuRecipeActivity> mIntentTest =
            new ActivityTestRule<>(MenuRecipeActivity.class);

    @Test
    public void showList_clickItem(){

        onView(withId(R.id.recycler_ingredient)).check(matches(isDisplayed()));

    }

    @Test
    public void clickBtnSteps_goDetailsStep(){
        onView(withId(R.id.btn_show_steps)).perform(click());

    }
}
