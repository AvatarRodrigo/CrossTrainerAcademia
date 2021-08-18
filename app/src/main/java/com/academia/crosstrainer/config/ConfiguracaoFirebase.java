package com.academia.crosstrainer.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    private static FirebaseAuth auth;
    private static DatabaseReference fireBase;
    //Retorna a instância do FirebaseAuth
    public static FirebaseAuth FirebaseAutenticacao(){
        if(auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
    //Retorna a instância do Firebase
    public static DatabaseReference getFireBaseDatabase(){
        if(fireBase == null){
            fireBase = FirebaseDatabase.getInstance().getReference();
        }
        return fireBase;
    }
}
