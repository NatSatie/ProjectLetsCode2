package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.GenericObjectType;
import com.company.classes.Oscar;

public class DbOscar extends Database{

    public DbOscar(){
        super();
    }

    public void register(GenericObjectType elem) {

    }

    @Override
    public GenericObjectType getElement(String name) {
        return null;
    }

    private void add(Oscar elem) {

    }

    private boolean search(Oscar elem){
        return false;
    }
}
