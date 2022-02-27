package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.GenericObjectType;
import com.company.classes.Movie;

public interface DatabaseActorInterface<T extends GenericObjectType> {
    public Actor getElement(String name);

    public void register(Actor elem, Movie movie);
}
