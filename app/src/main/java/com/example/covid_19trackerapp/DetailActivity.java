package com.example.covid_19trackerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry, tvCases, tvTodayCases, tvDeaths, tvTodayDeaths, tvRecovered, tvActive, tvCritical, tv_detailOfcounty;
    Toolbar detailActivityToolbar;
    ImageView imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);

        detailActivityToolbar = findViewById(R.id.detailActivity_toolbar);
        setSupportActionBar(detailActivityToolbar);
        tv_detailOfcounty = findViewById(R.id.tv_detailOfcounty);
        tv_detailOfcounty.setText("Details of "+AffectedCountriesActivity.countryModelsList.get(positionCountry).getCountry());
        imageView_back = findViewById(R.id.imageView_back);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCountry = findViewById(R.id.tv_country);
        tvCases = findViewById(R.id.tv_cases);
        tvTodayCases = findViewById(R.id.tv_todayCases);
        tvDeaths = findViewById(R.id.tv_deaths);
        tvTodayDeaths = findViewById(R.id.tv_todayDeaths);
        tvRecovered = findViewById(R.id.tv_recovered);
        tvActive = findViewById(R.id.tv_active);
        tvCritical = findViewById(R.id.tv_critical);

        tvCountry.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getCases());
        tvTodayCases.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getTodayCases());
        tvDeaths.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getTodayDeaths());
        tvRecovered.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getRecovered());
        tvActive.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getActive());
        tvCritical.setText(AffectedCountriesActivity.countryModelsList.get(positionCountry).getCritical());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
