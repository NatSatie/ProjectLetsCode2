package com.company.databases;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class Database<T>{
    protected List<T> db;
}
