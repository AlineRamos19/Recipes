package br.com.android.aline.recipes;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.android.aline.recipes.ui.home.HomeActivity;
import br.com.android.aline.recipes.ui.splash.SplashActivity;

@RunWith(AndroidJUnit4.class)
public class RecipeIdlingResourceTeste {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule =
            new ActivityTestRule<>(SplashActivity.class);

    private IdlingResource mIdlingResource;



    @Before
    public void registerIdlingResource() {
       mIdlingResource = mActivityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void idlingResourceTest(){
        mActivityRule.getActivity().startActivity(new Intent(mActivityRule.getActivity(), HomeActivity.class));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
