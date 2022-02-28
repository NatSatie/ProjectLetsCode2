package com.company.classes.comparators;

import com.company.classes.Actor;

import java.util.Comparator;

public class MoreThanOneOscarComparator implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        if (o1.getNumberOfOscarsAwarded() > o2.getNumberOfOscarsAwarded() && o1.getNumberOfOscarsAwarded() > 1){
            return 1;
        }
        return -1;
    }
}
