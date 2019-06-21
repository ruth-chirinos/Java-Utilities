package com.quiz.question2;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.stream.Collectors;

public class SortMap {
    public static void main(String args[]){
        //Structure 1
        Map<String, Integer> events = new HashMap<String, Integer>();
        events.put("event1", 1);
        events.put("event2", 10);
        events.put("event4", 5);
        events.put("event6", 2);

        //Structure 2
        List<String> eventName = new ArrayList<>();
        List<Integer> eventValue = new ArrayList<>();
        eventName.add("event1"); eventValue.add(11);
        eventName.add("event2"); eventValue.add(5);
        eventName.add("event4"); eventValue.add(3);
        eventName.add("event6"); eventValue.add(12);

        //Structure 3
        Event event1 = new Event("event1", 5);
        Event event2 = new Event("event2", 12);
        Event event3 = new Event("event3", 2);
        Event event4 = new Event("event4", 22);

        List<Event> lstEvents = new ArrayList<>();
        lstEvents.add(event1);
        lstEvents.add(event2);
        lstEvents.add(event3);
        lstEvents.add(event4);


        // let's sort this map by values first
        Map<String, Integer> sorted = events
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(sorted);
        System.out.println("****************************************");
        eventName.sort(Comparator.naturalOrder());
        System.out.println(eventName);
        System.out.println("****************************************");
        eventValue.sort(Comparator.naturalOrder());
        System.out.println(eventValue);
        System.out.println("****************************************");
        System.out.println("-BEFORE-");
        System.out.println(lstEvents);
        System.out.println("-AFTER-");
        EventComparator comp = new EventComparator();
        Collections.sort(lstEvents, comp);
        System.out.println(lstEvents);
        System.out.println("****************************************");



    }
}
