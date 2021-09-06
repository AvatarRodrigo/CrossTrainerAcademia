package com.academia.crosstrainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;

import com.academia.crosstrainer.MainActivity;
import com.academia.crosstrainer.R;
import com.academia.crosstrainer.TrainingActivity;
import com.academia.crosstrainer.model.ChronometerTraining;

public class StopwatchActivity extends AppCompatActivity {

    Spinner intencidades, pesos, intervalos, circuitos;
    private TextView txtTime;
    private Button btnStart, btnFinish, btnStop;

    private Context context;
    private ChronometerTraining chronometer;
    private Thread threadChrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        context = this;
        //Alterar barra superior
        String nameTrain = getIntent().getStringExtra("key");
        getSupportActionBar().setTitle(nameTrain);

        intencidades = (Spinner) findViewById(R.id.cbxIntensidade);
        intervalos = (Spinner) findViewById(R.id.cbxIntervalo);
        pesos = (Spinner) findViewById(R.id.cbxPeso);
        circuitos = (Spinner) findViewById(R.id.cbxCircuito);
        txtTime = (TextView) findViewById(R.id.txtTime);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnFinish = (Button)findViewById(R.id.btnFinish);
        btnStop = (Button)findViewById(R.id.btnStop);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.intervalos, R.layout.spinner_item);
        intervalos.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.intencidades, R.layout.spinner_item);
        intencidades.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.pesos, R.layout.spinner_item);
        pesos.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.circuitos, R.layout.spinner_item);
        circuitos.setAdapter(adapter);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(chronometer == null){
                   chronometer = new  ChronometerTraining(context);
                   threadChrono = new Thread(chronometer);
                   threadChrono.start();
                   chronometer.start();
               }
            }
        });

        String itemIntervalos = intervalos.getSelectedItem().toString();
        String itemIntencidades = intencidades.getSelectedItem().toString();
        String itemPesos = pesos.getSelectedItem().toString();
        String itemCircuitos = circuitos.getSelectedItem().toString();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chronometer != null){
                    chronometer.stop();
                    chronometer = null;
                    threadChrono.interrupt();
                    threadChrono = null;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTraining();
            }
        });

    }
    public void openTraining(){
        startActivity(new Intent(this, TrainingActivity.class));
        finish();
    }

    public void updateTimerText(final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               txtTime.setText(time);
            }
        });
    }

}