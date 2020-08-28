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

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SysmptomsViewHolder> {

    private List<String> symtomsTitleList;
    private List<Integer> symptomsImageList;

    public SymptomsAdapter(List<String> symtomsTitle, List<Integer> symptomsImageList) {
        this.symtomsTitleList = symtomsTitle;
        this.symptomsImageList = symptomsImageList;
    }

    @NonNull
    @Override
    public SysmptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_card, parent, false);
        return new SysmptomsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SysmptomsViewHolder holder, int position) {
        holder.imageviewSysmtoms.setImageResource(symptomsImageList.get(position));
        holder.tvTitleSymptoms.setText(symtomsTitleList.get(position));

    }

    @Override
    public int getItemCount() {
        return symptomsImageList.size();
    }

    class SysmptomsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageviewSysmtoms;
        TextView tvTitleSymptoms;

        SysmptomsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageviewSysmtoms = itemView.findViewById(R.id.imageview);
            tvTitleSymptoms = itemView.findViewById(R.id.tv_title);
        }
    }
}
