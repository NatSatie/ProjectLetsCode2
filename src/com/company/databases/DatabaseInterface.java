package com.company.databases;

import com.company.classes.GenericObjectType;

public interface DatabaseInterface<T extends GenericObjectType> {
    public T getElement(String name);
}
