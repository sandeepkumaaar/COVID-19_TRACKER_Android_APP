package com.example.covid_19trackerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.covid_19trackerapp.adapters.PreventionAdapter;
import com.example.covid_19trackerapp.adapters.SymptomsAdapter;
import com.example.covid_19trackerapp.fragments.MyDialogFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_CALL = 1;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar homeToolbar;
    RecyclerView recyclerView_Symptoms, recyclerView_prevention;
    SymptomsAdapter symptomsAdapter;
    PreventionAdapter preventionAdapter;
    List<String> symtomsTitleList = new ArrayList<>();
    List<Integer> symptomsImageList = new ArrayList<>();
    List<String> preventionTitleList = new ArrayList<>();
    List<Integer> preventionImageList = new ArrayList<>();
    ImageView imageViewRight;
    Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        homeToolbar = findViewById(R.id.homeToolbar);
        setSupportActionBar(homeToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_logout).setVisible(false);
        menu.findItem(R.id.nav_profile).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, homeToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        toggle.setHomeAsUpIndicator(R.drawable.ic_navigation);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        // Symptoms
        recyclerView_Symptoms = findViewById(R.id.rv_symptoms);

        symptomsImageList.add(R.drawable.ic_drycough);
        symptomsImageList.add(R.drawable.ic_highfever);
        symptomsImageList.add(R.drawable.ic_sorethroat);
        symptomsImageList.add(R.drawable.ic_breathing);

        symtomsTitleList.add("Dry Cough");
        symtomsTitleList.add("High Fever");
        symtomsTitleList.add("Sore Throat");
        symtomsTitleList.add("Breathing");

        LinearLayoutManager llmOne = new LinearLayoutManager(this);
        llmOne.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_Symptoms.setLayoutManager(llmOne);

        symptomsAdapter = new SymptomsAdapter(symtomsTitleList, symptomsImageList);
        recyclerView_Symptoms.setAdapter(symptomsAdapter);

        // Prevention Recyclerview
        recyclerView_prevention = findViewById(R.id.rv_prevention);

        preventionImageList.add(R.drawable.ic_handwash);
        preventionImageList.add(R.drawable.ic_soap);
        preventionImageList.add(R.drawable.ic_crowded);
        preventionImageList.add(R.drawable.ic_wearmask);
        preventionImageList.add(R.drawable.ic_handshake);
        preventionImageList.add(R.drawable.ic_closecontact);

        preventionTitleList.add("Hand wash");
        preventionTitleList.add("use soap");
        preventionTitleList.add("avoid crowds");
        preventionTitleList.add("wear mask");
        preventionTitleList.add("avoid handshake");
        preventionTitleList.add("Make distance");

        LinearLayoutManager llmTwo = new LinearLayoutManager(this);
        llmTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_prevention.setLayoutManager(llmTwo);

        preventionAdapter = new PreventionAdapter(preventionTitleList, preventionImageList);
        recyclerView_prevention.setAdapter(preventionAdapter);

        imageViewRight = findViewById(R.id.imageView_right);
        imageViewRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });

        btn_call = findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAtRuntime();
            }
        });
    }

    private void callAtRuntime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:1075"));
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
            break;
            case R.id.nav_about:
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getSupportFragmentManager(),"My Dialog");
            break;
            case R.id.nav_helpline:
                Toast.makeText(HomeActivity.this, "Helpline", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_donate:
                Toast.makeText(HomeActivity.this, "Donate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_shareapp:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "COVID 19 Tracker-Android App";
                String shareSubject = "Android App";
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                startActivity(Intent.createChooser(shareIntent,"Share App"));
                break;
            case R.id.nav_rateus:
                Toast.makeText(HomeActivity.this, "Rate us", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callAtRuntime();
            }else {
                Toast.makeText(HomeActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
