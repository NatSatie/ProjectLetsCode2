package com.company.databases;

import com.company.classes.GenericObjectType;
import com.company.classes.Movie;

public interface DatabaseActorInterface<T extends GenericObjectType> {
    public T getElement(String name);

    public void register(T elem, Movie movie);
}
