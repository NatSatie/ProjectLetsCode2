package com.company.classes;

public class Movie extends GenericObjectType{
    private String name;
    private int yearRelease;

    public Movie(String name, int yearRelease){
        this.name = name;
        this.yearRelease = yearRelease;
    }

    public String getName() {
        return name;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    @Override
    public String toString(){
        return "Filme: " + this.name + " (" + this.yearRelease + ")\r\n";
    }
}
