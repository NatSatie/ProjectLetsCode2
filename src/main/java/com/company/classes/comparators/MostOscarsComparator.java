package com.company.classes.comparators;

import com.company.classes.Actor;

import java.util.Comparator;

public class MostOscarsComparator  implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        return Integer.compare(o1.getNumberOfOscarsAwarded(), o2.getNumberOfOscarsAwarded());
    }
}
