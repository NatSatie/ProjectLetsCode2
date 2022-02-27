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

    public void register(GenericObjectType elem, Movie movie) {
        if (search((Actor) elem)){
            add((Actor) elem);
        }
        update((Actor) elem, movie);
        // System.out.println(elem.toString());
    }

    @Override
    public GenericObjectType getElement(String name) {
        Actor a = (Actor) this.db
                .stream()
                .filter(e -> ((Actor) e).getName().equals(name))
                .findFirst()
                .get();
        return a;
    }

    private void add(Actor elem) {
        db.add(elem);
    }

    private boolean search(Actor elem){
        if (this.db
                .stream()
                .filter( e -> ((Actor)e).getName().equals(elem.getName()))
                .findAny()
                .isPresent()){
            return true;
        } return false;
    }

    private void update(Actor elem, Movie m) {
        int index = db.indexOf(elem);
        if (index != -1){
            elem.addMovie(m);
            db.set(index, elem);
        }
    }
}
