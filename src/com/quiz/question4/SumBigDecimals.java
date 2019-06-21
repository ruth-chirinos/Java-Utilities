package com.quiz.question4;

import java.math.BigInteger;

public class SumBigDecimals {
    public static void main(String args[]){
        BigInteger a = new BigInteger("10000000000");
        BigInteger b = new BigInteger("10000000000");
        BigInteger c = a.add(b);
        System.out.println(c);
    }
}
