package br.com.android.aline.recipes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.android.aline.recipes.ui.home.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    private static final String RECIPE_NAME = "Nutella Pie";
    @Rule
    public ActivityTestRule<HomeActivity> mActivity =
            new ActivityTestRule<>(HomeActivity.class);


    @Test
    public void testItemRecycler(){

        onView(withId(R.id.list_recipes)).check(matches(hasDescendant(withText(RECIPE_NAME))));
   }





}
