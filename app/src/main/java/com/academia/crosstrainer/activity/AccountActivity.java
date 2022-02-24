package com.academia.crosstrainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

import com.academia.crosstrainer.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Toolbar toolbar
        getSupportActionBar().setTitle(R.string.updateUser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}