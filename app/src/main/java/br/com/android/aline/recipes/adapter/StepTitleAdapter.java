package br.com.android.aline.recipes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.android.aline.recipes.R;
import br.com.android.aline.recipes.models.Step;
import br.com.android.aline.recipes.ui.steps.StepDetailsActivity;


public class StepTitleAdapter extends RecyclerView.Adapter<StepTitleAdapter.StepHolder> {

    private Context mContext;
    private List<Step> mListStep;

    public StepTitleAdapter(Context mContext, List<Step> mListStep) {
        this.mContext = mContext;
        this.mListStep = mListStep;
    }


    @NonNull
    @Override
    public StepHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_step_menu, viewGroup, false);
        return new StepHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepHolder holder, int position) {
        final Step itemStep = mListStep.get(position);
        holder.titleStep.setText(itemStep.getShortDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StepDetailsActivity.class);
                intent.putExtra("step", itemStep);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListStep.size();
    }


    public class StepHolder extends RecyclerView.ViewHolder {
        TextView titleStep;

        public StepHolder(@NonNull View itemView) {
            super(itemView);
            titleStep = itemView.findViewById(R.id.short_description_step);
        }
    }

}

