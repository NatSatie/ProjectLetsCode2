package com.company.classes;

import java.util.ArrayList;
import java.util.List;

public class Actor extends GenericObjectType {
    private String name;
    private int birthYear;
    private GenderEnum gender;
    private List<Movie> history;

    public Actor(String name, int birthYear, GenderEnum gender){
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.history = new ArrayList<Movie>();
    }

    public String getName() {
        return name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public List<Movie> getHistory() {
        return history;
    }

    public void addMovie(Movie m){
        history.add(m);
        System.out.println(history.size());
    }

    private String actorsDescription(){
        // "Genero " + (gender.equals(GenderEnum.FEMALE) ? " Feminino" : " Masculino") + "\r\n" +
        String s = "------------ Ator: " + this.name + " ------------\r\n" +
                "Ano de nascimento " + this.birthYear + "\r\n" +
                "HISTORICO DE FILMES PREMIADOS AO OSCAR: \r\n";
        for ( Movie m : this.history){
            s.concat(m.toString());
        }
        return s;
    }

    @Override
    public String toString(){
        return this.actorsDescription();
    }
}
