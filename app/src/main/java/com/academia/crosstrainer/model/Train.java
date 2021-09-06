package com.academia.crosstrainer.model;

import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.academia.crosstrainer.helper.Base64Custom;
import com.google.firebase.database.DatabaseReference;

public class Train {
    private String circuit;
    private String date;
    private String time;
    private String interval;
    private String energy;
    private String weight;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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
