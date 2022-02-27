package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.GenericObjectType;
import com.company.classes.Movie;

import java.util.ArrayList;

public class DbActor extends Database implements DatabaseActorInterface{
    public DbActor(){
        super();
        super.db = new ArrayList<Actor>();
    }

    @Override
    public void register(Actor elem, Movie movie) {
        if (this.search(elem.getName())){
            this.add(elem);
            elem.addMovie(movie);
        } else {
            Actor a = this.getElement(elem.getName());
            a.addMovie(movie);
            super.db.set(this.getIndex(elem.getName()), a);
        }
    }

    @Override
    public Actor getElement(String name) {
        Actor a = (Actor) this.db
                .stream()
                .filter(e -> ((Actor) e).getName().equals(name))
                .findFirst()
                .get();
        return a;
    }

    public int getIndex(String name) {
        Actor a = this.getElement(name);
        int index = super.db.indexOf(a);
        return index;
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

    private void update(Actor elem, Movie m) {
        elem.addMovie(m);
    }
}
