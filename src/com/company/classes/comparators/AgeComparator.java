package com.company.classes.comparators;

import com.company.classes.Actor;

import java.util.Comparator;

public class AgeComparator  implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        if (o1.getBirthYear() > o2.getBirthYear()){
            return -1;
        } else if  (o1.getBirthYear() < o2.getBirthYear()){
            return 1;
        } return 0;
    }
}