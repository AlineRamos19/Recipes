package br.com.android.aline.recipes.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.cache.InternalStorage;
import br.com.android.aline.recipes.models.Result;

public class WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext = null;
    private int mAppWidgetId;
    private static final String KEY = "LIST_RECIPES";
    private List<WidgetModel> mWidgetList = new ArrayList<WidgetModel>();
    private List<Result> mListRecipe;

    public WidgetRemoteViewFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }

    private void updateWidgetListView() {
        try {
            mListRecipe = (List<Result>) InternalStorage.readObject(mContext, KEY);
            for (int i = 0; i < mListRecipe.size(); i++) {
                if (mListRecipe.get(i).getName().equals(EnumRecipe.NUTELLA_PIE.toString())) {
                    mWidgetList.add(new WidgetModel(mListRecipe.get(i).getName(), R.drawable.nutella_pie_img));
                } else if (mListRecipe.get(i).getName().equals(EnumRecipe.BROWNIES.toString())) {
                    mWidgetList.add(new WidgetModel(mListRecipe.get(i).getName(), R.drawable.brownie));
                } else if (mListRecipe.get(i).getName().equals(EnumRecipe.YELLOW_CAKE.toString())) {
                    mWidgetList.add(new WidgetModel(mListRecipe.get(i).getName(), R.drawable.yellow));
                } else if (mListRecipe.get(i).getName().equals(EnumRecipe.CHEESECAKE.toString())) {
                    mWidgetList.add(new WidgetModel(mListRecipe.get(i).getName(), R.drawable.cheese));
                } else {
                    mWidgetList.add(new WidgetModel(mListRecipe.get(i).getName(), R.drawable.broken_image));
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
        remoteViews.setTextViewText(R.id.title_recipe_widget, mWidgetList.get(position).getmRecipeTitle());
        remoteViews.setImageViewResource(R.id.image_recipe_widget, mWidgetList.get(position).getmImageRecipe());

        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putSerializable("RECIPE_KEY", mListRecipe.get(position));
        intent.putExtras(extras);
        remoteViews.setOnClickFillInIntent(R.id.widget_linear, intent);


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


    public enum EnumRecipe {

        NUTELLA_PIE {
            @Override
            public String toString() {
                return "Nutella Pie";
            }
        },
        BROWNIES {
            @Override
            public String toString() {
                return "Brownies";
            }
        },
        YELLOW_CAKE {
            @Override
            public String toString() {
                return "Yellow Cake";
            }
        },
        CHEESECAKE {
            @Override
            public String toString() {
                return "Cheesecake";
            }
        }
    }
}
