package com.academia.crosstrainer;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.academia.crosstrainer.databinding.ActivityActionBinding;
import com.academia.crosstrainer.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ActionActivity extends AppCompatActivity {

    private ActivityActionBinding binding;
    TextView lbTrain;
   //Spinner intencidades, pesos, intervalos, circuitos;

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
/*
        intencidades = (Spinner) findViewById(R.id.cbxIntensidade);
        intervalos = (Spinner) findViewById(R.id.cbxIntervalo);
        pesos = (Spinner) findViewById(R.id.cbxPeso);
        circuitos = (Spinner) findViewById(R.id.cbxCircuito);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.intervalos, R.layout.spinner_item);
       // intervalos.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.intencidades, R.layout.spinner_item);
     //   intencidades.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.pesos, R.layout.spinner_item);
     //   pesos.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.circuitos, R.layout.spinner_item);
      //  circuitos.setAdapter(adapter);

        /*
        String itemIntervalos = intervalos.getSelectedItem().toString();
        String itemIntencidades = intencidades.getSelectedItem().toString();
        String itemPesos = pesos.getSelectedItem().toString();
        String itemCircuitos = circuitos.getSelectedItem().toString();
*/

        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }
}