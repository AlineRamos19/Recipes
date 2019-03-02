package br.com.android.aline.recipes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import br.com.android.aline.recipes.models.Result;
import br.com.android.aline.recipes.ui.home.HomeActivity;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    private List<Result> result = new ArrayList<>();
    private static final String RECIPE_KEY = "RECIPE_KEY";
    @Rule
    public ActivityTestRule<HomeActivity> mActivity =
            new ActivityTestRule<>(HomeActivity.class);





}
