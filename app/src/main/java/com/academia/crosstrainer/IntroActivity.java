package com.academia.crosstrainer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.academia.crosstrainer.activity.CadastroActivity;
import com.academia.crosstrainer.activity.LoginActivity;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class IntroActivity extends com.heinrichreimersoftware.materialintro.app.IntroActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro);

        setButtonBackVisible(false);
        setButtonNextVisible(true);
        setButtonCtaVisible(false);

        //autoplay(3500,1);

        addSlide(new FragmentSlide.Builder()
                //.background(700141)
                .background(android.R.color.background_dark)
                .fragment(R.layout.intro1)
                .canGoBackward(false)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_dark)
                .fragment(R.layout.intro2)
                .canGoBackward(false)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_dark)
                .fragment(R.layout.intro3)
                .canGoBackward(false)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_dark)
                .fragment(R.layout.intro4)
                .canGoBackward(false)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.background_dark)
                .fragment(R.layout.intro5)
                .canGoBackward(false)
                .canGoForward(false)
                .build()
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkLoggedUser();
    }

    public void btnEnter(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btnRegister(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void btnWant(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://acadtrainer.com.br")));
    }

    public void checkLoggedUser(){
        auth = ConfiguracaoFirebase.FirebaseAutenticacao();
       // auth.signOut();
        if(auth.getCurrentUser() != null){
            openScreenMain();
        }
    }

    public void openScreenMain(){
        startActivity(new Intent(this, ActionActivity.class));
        finish();
    }
}