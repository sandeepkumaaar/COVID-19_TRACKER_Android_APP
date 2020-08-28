package com.example.covid_19trackerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.covid_19trackerapp.adapters.OnboardingAdapter;
import com.example.covid_19trackerapp.fragments.SlideOneFragment;
import com.example.covid_19trackerapp.fragments.SlideThreeFragment;
import com.example.covid_19trackerapp.fragments.SlideTwoFragment;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager2 viewpager2Slider;
    OnboardingAdapter onboardingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        viewpager2Slider = findViewById(R.id.viewpager2_slider);
        setupViewpager(viewpager2Slider);
    }

    private void setupViewpager(ViewPager2 viewpager2Slider) {

        onboardingAdapter = new OnboardingAdapter(getSupportFragmentManager(),getLifecycle());
        onboardingAdapter.addFragment(new SlideOneFragment());
        onboardingAdapter.addFragment(new SlideTwoFragment());
        onboardingAdapter.addFragment(new SlideThreeFragment());
        viewpager2Slider.setAdapter(onboardingAdapter);
    }
}
