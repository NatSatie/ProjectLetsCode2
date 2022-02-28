package com.company.classes.comparators;

import com.company.classes.Oscar;

import java.util.Comparator;

public class AgeWhenGotOscarComparator implements Comparator<Oscar> {
    @Override
    public int compare(Oscar o1, Oscar o2) {
        return Integer.compare(o1.getActorAge(), o2.getActorAge());
    }
}
