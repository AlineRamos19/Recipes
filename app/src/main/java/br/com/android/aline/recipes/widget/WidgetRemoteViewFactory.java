package br.com.android.aline.recipes.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.models.Ingredient;
import br.com.android.aline.recipes.models.Result;

public class  WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext = null;
    private int mAppWidgetId;
    private static final String KEY = "LIST_RECIPES";
    private List<Ingredient> mWidgetList = new ArrayList<Ingredient>();
    private List<Ingredient> mListRecipe;

    public WidgetRemoteViewFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    private void updateWidgetListView() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("result_prefs", "");
        Result result = gson.fromJson(json, Result.class);
        mWidgetList = result.getIngredients();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        updateWidgetListView();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.row_ingredient);

            for(int i = 0;i < mWidgetList.size(); i ++){
                remoteViews.setTextColor(R.id.ingredient, mContext.getResources().getColor(R.color.colorValuesWidget));
                remoteViews.setTextColor(R.id.quantity, mContext.getResources().getColor(R.color.colorValuesWidget));
                remoteViews.setTextColor(R.id.measure, mContext.getResources().getColor(R.color.colorValuesWidget));
                remoteViews.setTextViewText(R.id.ingredient, mWidgetList.get(position).getIngredient());
                remoteViews.setTextViewText(R.id.quantity, String.valueOf(mWidgetList.get(position).getQuantity()));
                remoteViews.setTextViewText(R.id.measure, mWidgetList.get(position).getMeasure());
            }

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }



}
