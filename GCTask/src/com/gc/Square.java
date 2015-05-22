package com.gc;

public class Square {
    String num;
    String square;
    public Square(long num) {
        this.num = new Long(num).toString();
        this.square = new Long(num*num).toString();
    }
}