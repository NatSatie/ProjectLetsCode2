package com.company.classes.comparators;

import com.company.classes.Actor;

import java.util.Comparator;

public class AgeWhenGotOscarComparator implements Comparator<Actor> {
    @Override
    public int compare(Actor o1, Actor o2) {
        return Integer.compare(o1.getNumberOfOscarsAwardedByAgeGap(20,30), o2.getNumberOfOscarsAwardedByAgeGap(20, 30));
    }
}
