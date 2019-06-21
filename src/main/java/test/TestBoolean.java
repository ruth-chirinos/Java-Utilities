package main.java.test;
//Test

public class TestBoolean {
    public static void main(String[] args) {
        boolean value1 = false;
        boolean value2 = true;

        System.out.println("value1 == false");
        if(value1 == false){
            System.out.println((value1 == false)+" value1 == false");
        }
        System.out.println("!value1");
        if(!value1){
            System.out.println((!value1) + " !value1");
        }
        System.out.println("***************************************");
        System.out.println("value2 == false");
        if(value2 == false){
            System.out.println((value2 == false)+" value2 == false");
        }
        System.out.println("!value2");
        if(!value2){
            System.out.println((!value2) + " !value2");
        }
    }
}
