package com.company.databases;

import com.company.classes.Oscar;

import java.util.ArrayList;

public class DbOscar extends Database{

    public DbOscar(){
        super(new ArrayList<Oscar>());
    }

    public void register(Oscar elem) {
        this.add(elem);
    }

    public Oscar getElement(int year) {
        Oscar o = (Oscar) super.db
                .stream()
                .filter(e -> ((Oscar) e).getMovie().getYearRelease() == year)
                .findAny()
                .get();
        return o;
    }

    private void add(Oscar elem) {
        super.db.add(elem);
    }
}
