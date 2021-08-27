package com.academia.crosstrainer.model;

import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.helper.Base64Custom;
import com.google.firebase.database.DatabaseReference;

public class Train {
    private String circuit;
    private String date;
    private String level;
    private String time;

    public Train() {
    }

    public String getCircuit() {
        return circuit;
    }

    public void setCircuit(String circuit) {
        this.circuit = circuit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void salvar(String mail, String month){
        String idUser = Base64Custom.codeBase64(mail);
        DatabaseReference firebase = ConfiguracaoFirebase.getFireBaseDatabase();
        firebase.child("train")
                .child(idUser)
                .child(month)
                .push()
                .setValue(this);
    }
}
