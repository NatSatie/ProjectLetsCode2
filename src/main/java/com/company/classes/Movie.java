package com.company.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Movie implements Comparable<Movie>{
    private String name;
    private int yearRelease;

    @Override
    public String toString(){
        return this.name + " (" + this.yearRelease + ") ";
    }

    @Override
    public int compareTo(Movie o) {
        return Integer.compare(this.yearRelease, o.getYearRelease());
    }
}
