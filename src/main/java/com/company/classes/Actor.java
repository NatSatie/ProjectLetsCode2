package com.company.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Actor implements Comparable<Actor> {
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

    public void addMovie(Movie m){
        this.history.add(m);
    }

    public int getNumberOfOscarsAwarded() {
        return this.history.size();
    }

    private String actorsDescription(){
        String s = "Ator: " + this.name + "\r\n" +
                "Ano de nascimento: " + this.birthYear + "\r\n" +
                "Genero: " + (gender.equals(GenderEnum.FEMALE) ? " Feminino" : " Masculino") + "\r\n" +
                "HISTORICO DE FILMES PREMIADOS AO OSCAR ("+ this.getNumberOfOscarsAwarded() +") \r\n";
        for ( Movie m : this.history){
            int age = m.getYearRelease()-this.birthYear;
            s += m.toString() + "aos " + age + " anos\r\n";
        }
        return s;
    }

    @Override
    public String toString(){
        return this.actorsDescription();
    }

    @Override
    public int compareTo(Actor o) {
        if (this.birthYear > o.getBirthYear()){
            return -1;
        } else if (this.birthYear < o.getBirthYear()){
            return 1;
        } return 0;
    }
}