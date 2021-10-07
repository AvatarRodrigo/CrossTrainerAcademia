package com.academia.crosstrainer.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.academia.crosstrainer.R;
import com.academia.crosstrainer.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import androidx.appcompat.content.res.AppCompatResources;

public class Train {
    private String key;
    private String circuit;
    private String date;
    private String time;
    private String interval;
    private String energy;
    private String weight;
    private String medal;

    public Train() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
       if(time.contains(":")){
           String[] times = time.split(":");
           return times[0] + "'" + times[1] + "\"" + times[2];
       }else {
           return time;
       }
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

    public String getMedal() {
        if(time.contains(":")) {
            String[] times = time.split(":");
            int min = Integer.parseInt(times[0]);
            int seg = Integer.parseInt(times[1]);
            int cent = Integer.parseInt(times[2]);
            if (min < 2 || min == 2 && seg < 9) {
                medal = "Platina";
            } else if (min == 2 && seg < 16) {
                medal = "Ouro";
            } else if (min == 2 && seg < 26) {
                medal = "Prata";
            } else if (min == 2 && seg < 51) {
                medal = "Bronze";
            } else {
                medal = "Shadow";
            }
            return medal;
        }else{
            return medal;
        }
    }

    public void save(String mail, String month){
        DatabaseReference firebase = ConfiguracaoFirebase.getFireBaseDatabase();
        firebase.child("train")
                .child(mail)
                .child(month)
                .push()
                .setValue(this);
    }

}
