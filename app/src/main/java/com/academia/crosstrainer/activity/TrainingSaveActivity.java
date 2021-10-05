package com.academia.crosstrainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.academia.crosstrainer.R;
import com.academia.crosstrainer.TrainingActivity;
import com.academia.crosstrainer.config.ConfigChallenge;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.helper.Base64Custom;
import com.academia.crosstrainer.helper.DateUtil;
import com.academia.crosstrainer.model.Train;
import com.google.firebase.auth.FirebaseAuth;

public class TrainingSaveActivity extends AppCompatActivity {

    private TextView resultExerciseTime,resultExerciseLevel, resultExerciseWeight, resultExerciseEnergy, resultExerciseInterval, resultExerciseDate;
    private ImageView resultExercise;
    private Train train;
    private Button btnResultSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_save);
        train = new Train();train.setCircuit(getIntent().getStringExtra("circuit"));
        train.setDate(DateUtil.dateActual());
        train.setEnergy(getIntent().getStringExtra("energy"));
        train.setInterval(getIntent().getStringExtra("interval"));
        train.setTime(getIntent().getStringExtra("time"));
        train.setWeight(getIntent().getStringExtra("weight"));

        resultExercise = (ImageView)findViewById(R.id.img_result_exercise);
        resultExercise.setImageDrawable(ConfigChallenge.getMedalDrawable(this,train.getCircuit()));

        resultExerciseInterval = findViewById(R.id.txt_result_exercise_interval);
        resultExerciseInterval.setText("I - " + train.getInterval());

        resultExerciseEnergy = findViewById(R.id.txt_result_exercise_energy);
        resultExerciseEnergy.setText("E - " + train.getEnergy());

        resultExerciseWeight = findViewById(R.id.txt_result_exercise_weight);
        resultExerciseWeight.setText("P - " + train.getWeight());

        resultExerciseTime = findViewById(R.id.txt_result_exercise_time);
        resultExerciseTime.setText(train.getTime());

        resultExerciseDate = findViewById(R.id.txt_result_exercise_date);
        resultExerciseDate.setText(train.getDate());

        resultExerciseLevel = findViewById(R.id.txt_result_exercise_level);
        resultExerciseLevel.setText(train.getMedal());

        btnResultSave = findViewById(R.id.btn_result_exercise_save);

        btnResultSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = ConfiguracaoFirebase.FirebaseAutenticacao();
                String userMail = auth.getCurrentUser().getEmail();
                userMail = Base64Custom.codeBase64(userMail);
                String trainMonth = DateUtil.getMonthYear(train.getDate());
               // Toast.makeText(TrainingSaveActivity.this,
              //          "Email: "+ userMail + " - Mês: " + trainMonth,
               //         Toast.LENGTH_SHORT).show();

               train.save(userMail,trainMonth);
               openScreenTrain();
            }
        });

    }

    public void openScreenTrain(){
        startActivity(new Intent(this, TrainingActivity.class));
    }


}