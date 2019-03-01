package br.com.android.aline.recipes.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.models.Ingredient;

public class ListIngredientsAdapter extends RecyclerView.Adapter<ListIngredientsAdapter.IngredientsHolder>{

    private List<Ingredient> mListIngredient;
    private Context mContext;

    public ListIngredientsAdapter(List<Ingredient> mListIngredient, Context mContext) {
        this.mListIngredient = mListIngredient;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public IngredientsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.row_ingredient, viewGroup, false);
       return new IngredientsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsHolder holder, int position) {

        Ingredient mItemIngredient = mListIngredient.get(position);
        holder.mIngredient.setText(mItemIngredient.getIngredient());
        holder.mQuantity.setText(String.valueOf(mItemIngredient.getQuantity()));
        holder.mMeasure.setText(mItemIngredient.getMeasure());
    }

    @Override
    public int getItemCount() {
        return mListIngredient.size();
    }

    public class IngredientsHolder extends RecyclerView.ViewHolder {

        private TextView mIngredient;
        private TextView mQuantity;
        private TextView mMeasure;
        public IngredientsHolder(@NonNull View itemView) {
            super(itemView);

            mIngredient = itemView.findViewById(R.id.ingredient);
            mQuantity = itemView.findViewById(R.id.quantity);
            mMeasure = itemView.findViewById(R.id.measure);
        }
    }
}
