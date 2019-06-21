package com.quiz.question2;

import java.util.Comparator;

public class EventComparator implements Comparator<Event>{

    @Override
    public int compare(Event o1, Event o2) {
        return o1.getValue().compareTo(o2.getValue());
    }
}
