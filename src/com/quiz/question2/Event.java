package com.quiz.question2;

public class Event{
    public  String name;
    public  Integer value;

    public Event(String name, int value){
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "[Name:"+this.name + " - Value:"+this.value+"] ";
    }
}
