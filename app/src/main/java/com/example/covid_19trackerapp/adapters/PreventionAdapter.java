package com.example.covid_19trackerapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19trackerapp.R;

import java.util.List;

public class PreventionAdapter extends RecyclerView.Adapter<PreventionAdapter.PreventionViewHolder> {

    private List<String> preventionTitleList;
    private List<Integer> preventionImageList;

    public PreventionAdapter(List<String> preventionTitleList, List<Integer> preventionImageList) {
        this.preventionTitleList = preventionTitleList;
        this.preventionImageList = preventionImageList;
    }

    @NonNull
    @Override
    public PreventionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_card, parent, false);
        return new PreventionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreventionViewHolder holder, int position) {
        holder.preventionImage.setImageResource(preventionImageList.get(position));
        holder.preventionTitle.setText(preventionTitleList.get(position));

    }

    @Override
    public int getItemCount() {
        return preventionImageList.size();
    }

    public class PreventionViewHolder extends RecyclerView.ViewHolder {

        ImageView preventionImage;
        TextView preventionTitle;

        public PreventionViewHolder(@NonNull View itemView) {
            super(itemView);

            preventionImage = itemView.findViewById(R.id.imageview);
            preventionTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
