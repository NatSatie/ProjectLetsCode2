package com.company.databases;

import com.company.classes.Oscar;

import java.util.ArrayList;
import java.util.Optional;

public class DbOscar extends Database<Oscar>{

    public DbOscar(){
        super(new ArrayList<>());
    }

    public void register(Oscar elem) {
        this.add(elem);
    }

    public Oscar getElement(int year) {
        Optional<Oscar> o = super.db
                .stream()
                .filter(e -> e.getMovie().getYearRelease() == year)
                .findAny();
        return o.orElse(null);
    }

    private void add(Oscar elem) {
        super.db.add(elem);
    }
}
