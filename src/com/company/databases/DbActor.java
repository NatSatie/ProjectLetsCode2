package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.Movie;

import java.util.ArrayList;

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
        Actor a = (Actor) this.db
                .stream()
                .filter(e -> ((Actor) e).getName().equals(name))
                .findFirst()
                .get();
        return a;
    }

    private void add(Actor elem) {
        super.db.add(elem);
    }

    private boolean search(String actor){
        if (super.db
                .stream()
                .filter( e -> ((Actor)e).getName().equals(actor))
                .findAny()
                .isPresent()){
            return false;
        } return true;
    }
}
