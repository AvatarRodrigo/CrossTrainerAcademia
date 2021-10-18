package com.academia.crosstrainer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.academia.crosstrainer.R;
import com.academia.crosstrainer.TrainingActivity;
import com.academia.crosstrainer.adapter.AdapterTrain;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.helper.Base64Custom;
import com.academia.crosstrainer.model.ChronometerTraining;
import com.academia.crosstrainer.model.Circuit;
import com.academia.crosstrainer.model.Train;
import com.academia.crosstrainer.model.UserApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StopwatchActivity extends AppCompatActivity {

    Spinner intencidades, pesos, intervalos, circuitos;
    private TextView txtTime, txtSex;
    private Button btnStart, btnFinish, btnStop;

    private Context context;
    private ChronometerTraining chronometer;
    private Thread threadChrono;

    private DatabaseReference userRef;
    private ValueEventListener valueEventListenerUser;

    //Listar exercícios
    private String nameTrain;
    private FirebaseAuth auth = ConfiguracaoFirebase.FirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFireBaseDatabase();
    private ListView listView;
    private RecyclerView recyclerView;
    private AdapterTrain adapterTrain;
    private List<String> trainList = new ArrayList<>();
    private Train train;
    private DatabaseReference trainRef;
    private ValueEventListener valueEventListenerTrain;
    private String exercises_sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        context = this;
        //Alterar barra superior
        nameTrain = getIntent().getStringExtra("key");
        getSupportActionBar().setTitle(nameTrain);

        intencidades = (Spinner) findViewById(R.id.cbxIntensidade);
        intervalos = (Spinner) findViewById(R.id.cbxIntervalo);
        pesos = (Spinner) findViewById(R.id.cbxPeso);
        circuitos = (Spinner) findViewById(R.id.cbxCircuito);
        txtTime = (TextView) findViewById(R.id.txtTime);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnFinish = (Button)findViewById(R.id.btnFinish);
        btnStop = (Button)findViewById(R.id.btnStop);
        listView = (ListView) findViewById(R.id.listExercices);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.intervalos, R.layout.spinner_item);
        intervalos.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.intencidades, R.layout.spinner_item);
        intencidades.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.pesos, R.layout.spinner_item);
        pesos.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(this, R.array.circuitos, R.layout.spinner_item);
        circuitos.setAdapter(adapter);

        listTrain();

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

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chronometer != null){
                    chronometer.stop();
                    chronometer = null;
                    threadChrono.interrupt();
                    threadChrono = null;
                    openScreenSave();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chronometer != null){
                    chronometer.stop();
                    chronometer = null;
                    threadChrono.interrupt();
                    threadChrono = null;
                }
                openTraining();
            }
        });

        circuitos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listTrain();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    public void openScreenSave(){
        Intent intent = new Intent(StopwatchActivity.this, TrainingSaveActivity.class);
        String itemIntervalos = intervalos.getSelectedItem().toString();
        String itemIntencidades = intencidades.getSelectedItem().toString();
        String itemPesos = pesos.getSelectedItem().toString();
        String itemCircuitos = circuitos.getSelectedItem().toString();
        intent.putExtra("circuit",itemCircuitos);
        intent.putExtra("interval",itemIntervalos);
        intent.putExtra("energy",itemIntencidades);
        intent.putExtra("weight",itemPesos);
        intent.putExtra("time",txtTime.getText());
        startActivity(intent);
        finish();
    }

    public void listTrain(){
        String mailUser = auth.getCurrentUser().getEmail();
        String idUser = Base64Custom.codeBase64(mailUser);
        userRef = firebaseRef.child("userApp").child(idUser);

        valueEventListenerUser = userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                UserApp userApp =  snapshot.getValue(UserApp.class);
                exercises_sex = userApp.getSex().equals("Feminino")?"exercises_female":"exercises_male";

                //criar a lista de exercícios
                trainRef = firebaseRef
                        .child("challenge")
                        .child(nameTrain);
                       // .child(itemCircuitos.toString());

                //Log.i("treinos0", "msg: "+ exercises_sex);
                valueEventListenerTrain = trainRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        trainList.clear();
                        for(DataSnapshot dados:snapshot.getChildren()){
                            String id = dados.child("name_circuits").getValue().toString();

                            String idCircuit = circuitos.getSelectedItem().toString();
                            if(idCircuit.equals(id)) {
                                for (DataSnapshot exes : dados.child(exercises_sex).getChildren()) {
                                    trainList.add(exes.getValue().toString());
                                   // Log.i("treinos1", "idCircuit: "+ trainList.toString());// exes.getValue());
                                }
                                //Criar a lista aqui
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.list_item, trainList);
                                listView.setAdapter(arrayAdapter);
                            }
                        }
                        //Collections.reverse(trainList);
                        //adapterTrain.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

}