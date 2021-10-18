package com.academia.crosstrainer.model;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
    private int id_circuits;
    private String name_circuits;
    private String nivel;
    private String emphasis;
    private List<String> exercises_female;
    private List<String> exercises_male;
    private List<String> medal;

    public Circuit() {
        exercises_female = new ArrayList<>();
        exercises_male = new ArrayList<>();
        medal = new ArrayList<>();
    }

    public int getId() {
        return id_circuits;
    }

    public String getName() {
        return name_circuits;
    }

    public String getNivel() {
        return nivel;
    }

    public String getEmphasis() {
        return emphasis;
    }

    public List<String> getExercises_female() {
        return exercises_female;
    }

    public List<String> getExercises_male() {
        return exercises_male;
    }

    public List<String> getMedal() {
        return medal;
    }
}
