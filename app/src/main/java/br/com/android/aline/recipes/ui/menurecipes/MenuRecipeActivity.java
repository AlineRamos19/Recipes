package br.com.android.aline.recipes.ui.menurecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.fragments.IngredientFragment;
import br.com.android.aline.recipes.models.Result;
import br.com.android.aline.recipes.models.Step;
import br.com.android.aline.recipes.ui.steps.StepActivity;

public class MenuRecipeActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_recipe);

        initToolbar();

        if(savedInstanceState == null){
            IngredientFragment ingredientFragment = new IngredientFragment();
            result = (Result) getIntent().getSerializableExtra("RECIPE_KEY");
            ingredientFragment.setListIngredient(result.getIngredients());
            getSupportFragmentManager().beginTransaction().add(R.id.frag_ingredient, ingredientFragment).commit();
        }

        TextView btnAllSteps = findViewById(R.id.btn_show_steps);
        btnAllSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(savedInstanceState != null){
                   Result mList  = (Result )savedInstanceState.getSerializable("result");
                   callIntent(mList.getSteps());
                }else{
                    callIntent(result.getSteps());
                }

            }
        });

    }

    private void callIntent(List<Step> listSteps) {
        Intent intent = new Intent(this, StepActivity.class);
        intent.putExtra("steps",(Serializable) listSteps);
        startActivity(intent);
    }

    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_details_recipe);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView titleToolbar = findViewById(R.id.title_toolbar);
        titleToolbar.setText(R.string.label_details_recipe);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("result", result);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result = (Result) savedInstanceState.getSerializable("result");

    }
}

