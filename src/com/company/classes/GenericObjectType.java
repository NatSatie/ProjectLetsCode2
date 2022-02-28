package com.company.classes;

public abstract class GenericObjectType<T extends GenericObjectInterface>{
    public abstract int compareTo(GenericObjectType o);
    // Intencionalmente vazio
}
