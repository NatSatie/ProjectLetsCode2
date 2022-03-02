package com.company.classes;

public class Movie implements Comparable<Movie>{
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
        return this.name + " (" + this.yearRelease + ") ";
    }

    @Override
    public int compareTo(Movie o) {
        if (this.yearRelease > o.getYearRelease()){
            return 1;
        } else if (this.yearRelease < o.getYearRelease()){
            return -1;
        } return 0;
    }
}
