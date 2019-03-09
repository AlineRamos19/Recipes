package br.com.android.aline.recipes.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.fragments.IngredientFragment;
import br.com.android.aline.recipes.fragments.MasterListFragment;
import br.com.android.aline.recipes.models.Result;
import br.com.android.aline.recipes.ui.menurecipes.MenuRecipeActivity;

public class HomeActivity extends AppCompatActivity implements HomeView, MasterListFragment.OnRecipeClickListener{


    private static final String RECIPE_KEY = "RECIPE_KEY";
    private boolean mTwoPane;
    private Result mResult;
    TextView btnSteps;
    private SharedPreferences sharedPreferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnSteps = findViewById(R.id.btn_show_steps);
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


        mTwoPane = findViewById(R.id.layout_details_recipe_tablet) != null;
        initFragment();


        initToolbar();

    }

    private void initFragment() {
        MasterListFragment masterListFragment = new MasterListFragment();
        getSupportFragmentManager().beginTransaction().
                add(R.id.master_list_fragment, masterListFragment).commit();

    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        TextView titleToolbar = findViewById(R.id.title_toolbar);
        titleToolbar.setText(R.string.app_name);
    }

    @Override
    public void onRecipeSelected(Result result) {

        if(mTwoPane){
            IngredientFragment ingredientFragment = new IngredientFragment();
            ingredientFragment.setListIngredient(result.getIngredients());
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_ingredient, ingredientFragment).commit();
        }else{
            this.mResult = result;

            Bundle bundle = new Bundle();
            bundle.putSerializable(RECIPE_KEY, result);
            Intent intent = new Intent(this, MenuRecipeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }


        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.contains("result_prefs")){
            editor.clear().commit();
        }

        Gson gson = new Gson();
        String json = gson.toJson(result);
        editor.putString("result_prefs", json);
        editor.commit();



    }
}
