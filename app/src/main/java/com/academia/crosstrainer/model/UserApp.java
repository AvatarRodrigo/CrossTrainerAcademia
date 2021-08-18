package com.academia.crosstrainer.model;

import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class UserApp {
    private String idUser;
    private String nome;
    private String email;
    private String celular;
    private String senha;

    public UserApp() {
    }

    public String getIdUser() { return idUser; }

    @Exclude
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void save(){
        DatabaseReference firebase = ConfiguracaoFirebase.getFireBaseDatabase();
        firebase.child("userApp")
        .child(this.idUser)
        .setValue(this);
    }
}
