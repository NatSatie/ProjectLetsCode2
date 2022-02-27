package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.Movie;

import java.util.List;

public abstract class Database<T>{
    protected List<T> db;

    public List<T> getDb() {
        return db;
    }
}
