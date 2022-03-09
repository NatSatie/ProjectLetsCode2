package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.Movie;

import java.util.ArrayList;
import java.util.Optional;

public class DbActor extends Database<Actor> {
    public DbActor(){
        super(new ArrayList<>());
    }

    public void register(Actor elem, Movie movie) {
        if (this.search(elem.getName())){
            this.add(elem);
            elem.addMovie(movie);
        } else {
            Actor a = this.getElement(elem.getName());
            a.addMovie(movie);
            super.db.set(super.db.indexOf(this.getElement(elem.getName())), a);
        }
    }

    public Actor getElement(String name) {
        Optional<Actor> a = this.db
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
        return a.orElse(null);
    }

    private void add(Actor elem) {
        super.db.add(elem);
    }

    private boolean search(String actor){
        return super.db
                .stream()
                .filter( e -> e.getName().equals(actor))
                .findAny()
                .isEmpty();
    }
}
