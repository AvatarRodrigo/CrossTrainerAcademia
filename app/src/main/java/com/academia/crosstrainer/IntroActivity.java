package com.academia.crosstrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class IntroActivity extends com.heinrichreimersoftware.materialintro.app.IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro);

        setButtonBackVisible(false);
        setButtonNextVisible(false);
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
                .canGoForward(false)
                .build()
        );

    }
}