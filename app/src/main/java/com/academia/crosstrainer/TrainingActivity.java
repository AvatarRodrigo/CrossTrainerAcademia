package com.academia.crosstrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.academia.crosstrainer.activity.AccountActivity;
import com.academia.crosstrainer.activity.StopwatchActivity;
import com.academia.crosstrainer.adapter.AdapterTrain;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.helper.Base64Custom;
import com.academia.crosstrainer.model.Train;
import com.academia.crosstrainer.model.UserApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    //private TextView txtWelcome;
    private FirebaseAuth auth = ConfiguracaoFirebase.FirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFireBaseDatabase();
    private DatabaseReference userRef;
    private ValueEventListener valueEventListenerUser;
    private ValueEventListener valueEventListenerTrain;

    private RecyclerView recyclerView;
    private AdapterTrain adapterTrain;
    private List<Train> trainList = new ArrayList<>();
    private Train train;
    private DatabaseReference trainRef;
    private String MonthYearSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //Alterar barra superior
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);
        //Variáveis
       // txtWelcome = findViewById(R.id.txtWelcome);
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerTraining);
        configCalendarView();
        swipeTrain();
        //Configurar Adapter
        adapterTrain = new AdapterTrain(trainList, this);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterTrain);
    }

    private void configCalendarView(){
        CharSequence months[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        calendarView.setTitleMonths(months);

        CalendarDay dateActual = calendarView.getCurrentDate();
        MonthYearSelected = getMonthYearSelected(dateActual);

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                MonthYearSelected = getMonthYearSelected(date);
                trainRef.removeEventListener(valueEventListenerTrain);
                recoverTrain();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                openScreenMain();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void recoverTrain(){
        String mailUser = auth.getCurrentUser().getEmail();
        String idUser = Base64Custom.codeBase64(mailUser);
        trainRef = firebaseRef
                .child("train")
                .child(idUser)
                .child(MonthYearSelected);

        valueEventListenerTrain = trainRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                trainList.clear();

                for(DataSnapshot dados:snapshot.getChildren()){
                    Train train = dados.getValue(Train.class);
                    train.setKey(dados.getKey());
                    trainList.add(train);
                }
                Collections.reverse(trainList);
                adapterTrain.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void swipeTrain(){
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder) {
                int dragsFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragsFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                deleteTrain(viewHolder);
            }
        };

        new ItemTouchHelper((itemTouch)).attachToRecyclerView(recyclerView);
    }

    public void recoverData(){
        String mailUser = auth.getCurrentUser().getEmail();
        String idUser = Base64Custom.codeBase64(mailUser);
        userRef = firebaseRef.child("userApp").child(idUser);

        valueEventListenerUser = userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                UserApp  userApp =  snapshot.getValue(UserApp.class);
               // txtWelcome.setText("Olá " + userApp.getNome());
                getSupportActionBar().setTitle("Olá " + userApp.getNome());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void openScreenGladiadores(View view){
                Intent intent = new Intent(TrainingActivity.this, StopwatchActivity.class);
                intent.putExtra("key","Gladiadores");
                startActivity(intent);
                finish();
    }

    public void openScreenDeusesGregos(View view){
        Intent intent = new Intent(TrainingActivity.this,StopwatchActivity.class);
        intent.putExtra("key","Deuses Gregos");
        startActivity(intent);
        finish();
    }

    public void openScreenMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }



    private String getMonthYearSelected(CalendarDay date){
        int month = date.getMonth() + 1;
        if(month <= 9){
            return String.valueOf("0"+month +""+ date.getYear());
        }else{
            return String.valueOf(month +""+ date.getYear());
        }
    }

    private void deleteTrain(RecyclerView.ViewHolder viewHolder){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Excluir Treino");
        alertDialog.setMessage("Você tem certeza que deseja excluir o treino selecionado?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Excluir treino
                int position = viewHolder.getAdapterPosition();
                train = trainList.get(position);

                String mailUser = auth.getCurrentUser().getEmail();
                String idUser = Base64Custom.codeBase64(mailUser);
                trainRef = firebaseRef
                        .child("train")
                        .child(idUser)
                        .child(MonthYearSelected);

                trainRef.child(train.getKey()).removeValue();
                adapterTrain.notifyItemRemoved(position);
                Toast.makeText(TrainingActivity.this,
                        "Treino excluido!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Manter treino
                Toast.makeText(TrainingActivity.this,
                        "O treino não foi excluido!",
                        Toast.LENGTH_SHORT).show();
                adapterTrain.notifyDataSetChanged();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        recoverData();
        recoverTrain();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userRef.removeEventListener(valueEventListenerUser);
        trainRef.removeEventListener(valueEventListenerTrain);
    }
}