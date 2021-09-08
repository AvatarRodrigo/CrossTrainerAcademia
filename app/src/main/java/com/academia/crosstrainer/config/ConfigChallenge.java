package com.academia.crosstrainer.config;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.academia.crosstrainer.R;

import androidx.appcompat.content.res.AppCompatResources;

public class ConfigChallenge {
    public static Drawable getMedalDrawable(Context context, String circuit) {
        switch (circuit){
            case "Impactus":
                return AppCompatResources.getDrawable(context,R.drawable.medalha_gladiadores_branca_impactus);
            case "Invictus":
                return AppCompatResources.getDrawable(context,R.drawable.medalha_gladiadores_branca_invictus);
            case "Maximus":
                return AppCompatResources.getDrawable(context,R.drawable.medalha_gladiadores_branca_maximus);
            case "Spartacus":
                return AppCompatResources.getDrawable(context,R.drawable.medalha_gladiadores_branca_spartacus);
            default:
                return AppCompatResources.getDrawable(context,R.drawable.ic_add_32);
        }


    }
}
