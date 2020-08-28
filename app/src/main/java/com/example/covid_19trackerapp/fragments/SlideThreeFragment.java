package com.example.covid_19trackerapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.covid_19trackerapp.HomeActivity;
import com.example.covid_19trackerapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlideThreeFragment extends Fragment {

    Button btn_getStarted;
    Animation btnAnim;

    public SlideThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slide_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_getStarted = view.findViewById(R.id.btn_getStarted);
        btnAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.btn_animation);

        btn_getStarted.setAnimation(btnAnim);
        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));

                //savePrefData();
            }
        });
    }

    private void savePrefData() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isOnboardingOpen",true);
        editor.commit();

    }
}
