package br.com.android.aline.recipes.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.fragments.MasterListFragment;
import br.com.android.aline.recipes.models.Result;

public class ListRecipesAdapter extends RecyclerView.Adapter<ListRecipesAdapter.ListHolder> {
    private Context mContext;
    private List<Result> mListResult;
    private final MasterListFragment.OnRecipeClickListener mCallback;

    public ListRecipesAdapter(Context context, List<Result> mListResult, MasterListFragment.OnRecipeClickListener mCallback) {
        this.mContext = context;
        this.mListResult = mListResult;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_recipe_home, parent,
                false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListHolder holder, final int position) {
        final Result result = mListResult.get(position);
        holder.mLabelRecipeName.setText(result.getName());
        chooseImageRecipe(holder, result.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onRecipeSelected(result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListResult.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {

        private ImageView mImageRecipe;
        private TextView mLabelRecipeName;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            mImageRecipe = itemView.findViewById(R.id.image_recipe);
            mLabelRecipeName = itemView.findViewById(R.id.label_recipe_name);
        }
    }

    public void chooseImageRecipe(ListHolder holder, String nameRecipe) {
        if (nameRecipe.equals(EnumRecipe.NUTELLA_PIE.toString())) {
            holder.mImageRecipe.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nutella_pie_img));
        } else if (nameRecipe.equals(EnumRecipe.BROWNIES.toString())) {
            holder.mImageRecipe.setImageDrawable(mContext.getResources().getDrawable(R.drawable.brownie));

        } else if (nameRecipe.equals(EnumRecipe.YELLOW_CAKE.toString())) {
            holder.mImageRecipe.setImageDrawable(mContext.getResources().getDrawable(R.drawable.yellow));

        } else if (nameRecipe.equals(EnumRecipe.CHEESECAKE.toString())) {
            holder.mImageRecipe.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cheese));

        } else {
            holder.mImageRecipe.setImageDrawable(mContext.getResources().getDrawable(R.drawable.broken_image));

        }
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
