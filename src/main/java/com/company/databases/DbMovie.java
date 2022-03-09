package com.company.databases;

import com.company.classes.Movie;

import java.util.ArrayList;
import java.util.Optional;

public class DbMovie extends Database<Movie>{

    public DbMovie(){
        super(new ArrayList<>());
    }

    public void register(Movie elem) {
        if (!search(elem)){
            this.add(elem);
        }
    }

    public Movie getElement(String name) {
        Optional<Movie> m = super.db
                .stream()
                .filter(e -> e.getName().equals(name))
                .findAny();
        return m.orElse(null);
    }

    private void add(Movie elem) {
        super.db.add(elem);
    }

    private boolean search(Movie elem){
        return super.db
                .stream()
                .anyMatch(e -> e.getName().equals(elem.getName()));
    }

}
