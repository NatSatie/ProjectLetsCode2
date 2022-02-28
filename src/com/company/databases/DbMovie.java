package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.GenericObjectType;
import com.company.classes.Movie;

import java.util.ArrayList;

public class DbMovie extends Database{

    public DbMovie(){
        super();
        super.db = new ArrayList<Movie>();
    }

    public void register(GenericObjectType elem) {
        if (!search((Movie) elem)){
            this.add((Movie) elem);
        }
    }

    public GenericObjectType getElement(String name) {
        Movie m = (Movie) super.db
                .stream()
                .filter(e -> ((Movie) e).getName().equals(name))
                .findFirst()
                .get();
        return m;
    }

    private void add(Movie elem) {
        super.db.add(elem);
    }

    private boolean search(Movie elem){
        if (super.db
                .stream()
                .filter( e -> ((Movie)e).getName().equals(elem.getName()))
                .findAny()
                .isPresent()){
            return true;
        } return false;
    }

}
