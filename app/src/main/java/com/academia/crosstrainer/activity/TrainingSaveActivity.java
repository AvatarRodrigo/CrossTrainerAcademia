package com.academia.crosstrainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.academia.crosstrainer.R;
import com.academia.crosstrainer.config.ConfigChallenge;
import com.academia.crosstrainer.helper.DateUtil;
import com.academia.crosstrainer.model.Train;

public class TrainingSaveActivity extends AppCompatActivity {

    TextView resultExerciseTime,resultExerciseLevel, resultExerciseWeight, resultExerciseEnergy, resultExerciseInterval, resultExerciseDate;
    ImageView resultExercise;
    Train train;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_save);

        train = new Train();
        train.setCircuit(getIntent().getStringExtra("circuit"));
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

    }
}