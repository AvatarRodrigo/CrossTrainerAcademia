package com.academia.crosstrainer.model;

public class ActionHistory {

    private String tempo;
    private String data;

    public ActionHistory(String tempo, String data) {
        this.tempo = tempo;
        this.data = data;
    }

    public String getTempo(){ return this.tempo; }
    public String getData(){ return this.data; }
}