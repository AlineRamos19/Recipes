package br.com.android.aline.recipes.ui.steps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.adapter.StepTitleAdapter;
import br.com.android.aline.recipes.models.Step;

public class StepActivity extends AppCompatActivity {

    private static final String LAYOUT_STATE = "LAYOUT_STATE";
    private RecyclerView mRecyclerView;
    private List<Step> mListStep;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        initToolbar();
        mRecyclerView = findViewById(R.id.recycler_steps);

        mListStep = (List<Step>) getIntent().getExtras().getSerializable("steps");
        setupRecycler();


    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_steps);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView titleToolbar = findViewById(R.id.title_toolbar);
        titleToolbar.setText(getString(R.string.btn_steps));
    }

    private void setupRecycler() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new StepTitleAdapter(this, mListStep));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LAYOUT_STATE, mRecyclerView.getLayoutManager().onSaveInstanceState());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLayoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_STATE));
    }
}
