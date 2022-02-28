package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.GenericObjectType;
import com.company.classes.Movie;
import com.company.classes.Oscar;

import java.util.ArrayList;

public class DbOscar extends Database{

    public DbOscar(){
        super();
        super.db = new ArrayList<Oscar>();
    }

    public void register(GenericObjectType elem) {
        this.add((Oscar) elem);
    }

    public GenericObjectType getElement(int year) {
        Oscar o = (Oscar) super.db
                .stream()
                .filter(e -> ((Oscar) e).getMovie().getYearRelease() == year)
                .findFirst()
                .get();
        return o;
    }

    private void add(Oscar elem) {
        super.db.add(elem);
    }
}
