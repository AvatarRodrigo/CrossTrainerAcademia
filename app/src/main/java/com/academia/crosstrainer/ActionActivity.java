package com.academia.crosstrainer;

import android.os.Bundle;
import android.widget.TextView;

import com.academia.crosstrainer.databinding.ActivityActionBinding;
import com.academia.crosstrainer.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ActionActivity extends AppCompatActivity {

    private ActivityActionBinding binding;
    //treinar
   TextView lbTrain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityActionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        //treinar
        lbTrain = findViewById(R.id.txtTitle);
        String valor = getIntent().getStringExtra("key");
        lbTrain.setText(valor);

        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }
}