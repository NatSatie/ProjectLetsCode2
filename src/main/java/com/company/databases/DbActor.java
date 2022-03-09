package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.Movie;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class DbActor extends Database {
    public DbActor(){
        super(new ArrayList<Actor>());
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
        Optional a = this.db
                .stream()
                .filter(e -> ((Actor) e).getName().equals(name))
                .findFirst();
        if (a.isPresent()){
            return (Actor) a.get();
        } return null;
    }

    private void add(Actor elem) {
        super.db.add(elem);
    }

    private boolean search(String actor){
        return super.db
                .stream()
                .filter( e -> ((Actor)e).getName().equals(actor))
                .findAny()
                .isEmpty();
    }
}
