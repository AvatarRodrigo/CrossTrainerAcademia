package com.academia.crosstrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.helper.Base64Custom;
import com.academia.crosstrainer.model.UserApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.jetbrains.annotations.NotNull;

public class TrainingActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
   // private TextView txtWelcome;
    private FirebaseAuth auth = ConfiguracaoFirebase.FirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFireBaseDatabase();

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
        configCalendarView();
        recoverData();
    }

    private void configCalendarView(){
        CharSequence months[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        calendarView.setTitleMonths(months);

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

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

    public void openScreenMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void recoverData(){
        String mailUser = auth.getCurrentUser().getEmail();
        String idUser = Base64Custom.codeBase64(mailUser);
        DatabaseReference userRef = firebaseRef.child("userApp").child(idUser);

        userRef.addValueEventListener(new ValueEventListener() {
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
                Intent intent = new Intent(TrainingActivity.this,ActionActivity.class);
                intent.putExtra("key","Gladiadores");
                startActivity(intent);
                finish();
    }
    public void openScreenDeusesGregos(View view){
        Intent intent = new Intent(TrainingActivity.this,ActionActivity.class);
        intent.putExtra("key","Deuses Gregos");
        startActivity(intent);
        finish();
    }

}