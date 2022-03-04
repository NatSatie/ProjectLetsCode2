package com.company.databases;

import com.company.classes.Actor;
import com.company.classes.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class Database<T>{
    protected List<T> db;
}
