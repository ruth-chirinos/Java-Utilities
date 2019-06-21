package com.quiz.question1;

public class Test{
    public static void main(String args[]){
        Item a = new Item();
        a.setValue(1);
        Item b = new Item();
        b=a;

        a.setValue(2);

        System.out.println("a ==" + a.getValue());
        System.out.println("b ==" + b.getValue());

        Item c = b;
        b.setValue(3);

        System.out.println("a ==" + a.getValue());
        System.out.println("b ==" + b.getValue());
        System.out.println("c ==" + c.getValue());

    }
}
