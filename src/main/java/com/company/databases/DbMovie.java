package com.company.databases;

import com.company.classes.Movie;

import java.util.ArrayList;
import java.util.Optional;

public class DbMovie extends Database{

    public DbMovie(){
        super(new ArrayList<Movie>());
    }

    public void register(Movie elem) {
        if (!search(elem)){
            this.add(elem);
        }
    }

    public Movie getElement(String name) {
        Optional m = super.db
                .stream()
                .filter(e -> ((Movie) e).getName().equals(name))
                .findAny();
        if (m.isPresent()){
            return (Movie) m.get();
        } return null;
    }

    private void add(Movie elem) {
        super.db.add(elem);
    }

    private boolean search(Movie elem){
        return super.db
                .stream()
                .anyMatch(e -> ((Movie)e).getName().equals(elem.getName()));
    }

}
