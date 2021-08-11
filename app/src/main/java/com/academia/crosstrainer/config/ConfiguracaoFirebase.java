package com.academia.crosstrainer.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autenticacao;
    //Retorna a instância do FirebaseAuth
    public static FirebaseAuth FirebaseAutenticacao(){
        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
