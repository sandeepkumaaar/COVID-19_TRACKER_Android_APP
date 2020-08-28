package com.example.covid_19trackerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covid_19trackerapp.AffectedCountriesActivity;
import com.example.covid_19trackerapp.R;
import com.example.covid_19trackerapp.countryModel.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelsList;
    private List<CountryModel> countryModelsListFiltered;

    public MyCustomAdapter(@NonNull Context context, List<CountryModel> countryModelsList) {
        super(context, R.layout.listview_card, countryModelsList);
        this.context = context;
        this.countryModelsList = countryModelsList;
        this.countryModelsListFiltered = countryModelsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_card, null, true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageViewFlag = view.findViewById(R.id.imag_flag);

        Glide.with(context)
                .load(countryModelsListFiltered.get(position).getFlag())
                .apply(RequestOptions.circleCropTransform())
                .into(imageViewFlag);
        tvCountryName.setText(countryModelsListFiltered.get(position).getCountry());
        return view;
    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;

                }else{
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryModel itemsModel:countryModelsList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModelsListFiltered = (List<CountryModel>) results.values;
                AffectedCountriesActivity.countryModelsList = (List<CountryModel>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
